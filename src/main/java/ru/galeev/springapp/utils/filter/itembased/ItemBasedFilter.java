package ru.galeev.springapp.utils.filter.itembased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.CalculationRepository;
import ru.galeev.springapp.persistence.repository.ContractRequestRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

/*
 *  КОллаборативная фильтрация
 */
@Service
public class ItemBasedFilter {

    @Autowired
    ContractRequestRepository contractRequestRepository;
    @Autowired
    CalculationRepository calculationRepository;
    @Autowired
    UserRepository userRepository;

    private static final int K = 5;

    public LinkedHashMap<Calculation, Double> getRecommendedList(User u) {
        List<Calculation> eventList = contractRequestRepository.getUnscoredByUser(u);

        Map<Calculation, Double> map = new LinkedHashMap<>();

        for (Calculation e : eventList) {
            Double score = getRecommendedScore(u, e);
            if (!score.isNaN()) {
                map.put(e, score);
            }
        }

        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private Double getRecommendedScore(User u, Calculation e) {
        Iterator<Map.Entry<Calculation, Double>> iterator = getSortedMap(e).entrySet().iterator();

        double chis = 0;
        double znam = 0;
        for (int i = 0; i < K && iterator.hasNext(); i++) {
            Map.Entry<Calculation, Double> entry = iterator.next();
            double rum = //Оценки пользователей u мероприятию j Пользователь, который оценил эвент E и эвент entry.getValue()
                    contractRequestRepository.findByMatrixPK(new MatrixPK(u, entry.getKey())).getScore();
            chis += rum * entry.getValue();
            znam += Math.abs(entry.getValue());
        }
        return chis / znam;
    }

    private Map<Calculation, Double> getSortedMap(Calculation e) {
        List<Calculation> eventList = calculationRepository.findAll();
        eventList.remove(e);

        Map<Calculation, Double> eventMap = new HashMap<>();

        for (Calculation event : eventList) {
            eventMap.put(event, getSim(event, e));
        }

        return eventMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private double getSim(Calculation a, Calculation m) {

//        List<User> userList = userRepository.findAll();
//        users.clear();

        List<User> users = new ArrayList<>();

        List<User> x = contractRequestRepository.getU(a);
        List<User> y = contractRequestRepository.getU(m);
        for (User u : x.size() > y.size() ? y : x) {
            if (y.contains(u)) {
                users.add(u);
            }
        }

//        for (User u : userList) {
//            List<Matrix> mList = matrixRepository.findByUser(u);
//            Matrix ma = matrixRepository.findByMatrixPK(new MatrixPK(u, a));
//            Matrix mm = matrixRepository.findByMatrixPK(new MatrixPK(u, m));
//
//            if (mList.contains(ma) && mList.contains(mm)) {
//                if (ma.getScore() != 0 && mm.getScore() != 0) {
//                    users.add(u);
//                }
//            }
//        }

        if (users.size() == 0) {
            return 0;
        }
        double ra = (double) a.getRating() / a.getEvaluators();
        double rm = (double) m.getRating() / m.getEvaluators();

        double chis = 0;
        double znamL = 0;
        double znamR = 0;

        for (User u : users) {
            double rua = contractRequestRepository.findByMatrixPK(new MatrixPK(u, a)).getScore();
//            System.out.println("User: " + u.getLogin() + "|||  Event: " + a.getName() + "||| rua: " + rua);
            double rum = contractRequestRepository.findByMatrixPK(new MatrixPK(u, m)).getScore();

            chis += (rua - ra) * (rum - rm);
            znamL += (rua - ra) * (rua - ra);
            znamR += (rum - rm) * (rum - rm);
        }

        System.out.println("Event A: " + a.getName() + "  Event B: " + m.getName() + "|||||||SCORE:" + chis / Math.sqrt(znamL * znamR));
        return chis / Math.sqrt(znamL * znamR);
    }
}
