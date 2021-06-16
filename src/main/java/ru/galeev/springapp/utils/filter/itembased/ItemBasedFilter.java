package ru.galeev.springapp.utils.filter.itembased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Matrix;
import ru.galeev.springapp.persistence.domain.MatrixPK;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.MatrixRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

/*
 *  КОллаборативная фильтрация
 */
@Service
public class ItemBasedFilter {

    @Autowired
    MatrixRepository matrixRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;

    private static final int K = 5;

    public LinkedHashMap<Event, Double> getRecommendedList(User u) {
        List<Event> eventList = matrixRepository.getUnscoredByUser(u);

        Map<Event, Double> map = new LinkedHashMap<>();

        for (Event e : eventList) {
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

    private Double getRecommendedScore(User u, Event e) {
        Iterator<Map.Entry<Event, Double>> iterator = getSortedMap(e).entrySet().iterator();

        double chis = 0;
        double znam = 0;
        for (int i = 0; i < K && iterator.hasNext(); i++) {
            Map.Entry<Event, Double> entry = iterator.next();
            double rum = //Оценки пользователей u мероприятию j Пользователь, который оценил эвент E и эвент entry.getValue()
                    matrixRepository.findByMatrixPK(new MatrixPK(u, entry.getKey())).getScore();
            chis += rum * entry.getValue();
            znam += Math.abs(entry.getValue());
        }
        return chis / znam;
    }

    private Map<Event, Double> getSortedMap(Event e) {
        List<Event> eventList = eventRepository.findAll();
        eventList.remove(e);

        Map<Event, Double> eventMap = new HashMap<>();

        for (Event event : eventList) {
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

    private double getSim(Event a, Event m) {

//        List<User> userList = userRepository.findAll();
//        users.clear();

        List<User> users = new ArrayList<>();

        List<User> x = matrixRepository.getU(a);
        List<User> y = matrixRepository.getU(m);
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
            double rua = matrixRepository.findByMatrixPK(new MatrixPK(u, a)).getScore();
//            System.out.println("User: " + u.getLogin() + "|||  Event: " + a.getName() + "||| rua: " + rua);
            double rum = matrixRepository.findByMatrixPK(new MatrixPK(u, m)).getScore();

            chis += (rua - ra) * (rum - rm);
            znamL += (rua - ra) * (rua - ra);
            znamR += (rum - rm) * (rum - rm);
        }

        System.out.println("Event A: " + a.getName() + "  Event B: " + m.getName() + "|||||||SCORE:" + chis / Math.sqrt(znamL * znamR));
        return chis / Math.sqrt(znamL * znamR);
    }
}
