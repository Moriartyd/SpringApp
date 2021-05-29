package ru.galeev.springapp.utils.filter.itembased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.MatrixPK;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.MatrixRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemBasedFilter {

    @Autowired
    MatrixRepository matrixRepository;
    @Autowired
    EventRepository eventRepository;

    private static final int K = 5;

    public LinkedHashMap<Event, Double> getRecommendedList(User u) {
        List<Event> eventList = matrixRepository.getUnscoredByUser(u);

        Map<Event, Double> map = new LinkedHashMap<>();

        for (Event e : eventList) {
            map.put(e, getRecommendedScore(u, e));
        }

        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private double getRecommendedScore(User u, Event e) {
        Iterator<Map.Entry<Event, Double>> iterator = getSortedMap(e).entrySet().iterator();

        double chis = 0;
        double znam = 0;
        for (int i = 0; i < K && iterator.hasNext(); i++) {
            Map.Entry<Event, Double> entry = iterator.next();
            double rum = matrixRepository.findByMatrixPK(new MatrixPK(u, e)).getScore();
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
        List<User> users = matrixRepository.getU(a, m);
        double ra = (double) a.getRating() / a.getEvaluators();
        double rm = (double) m.getRating() / m.getEvaluators();

        double chis = 0;
        double znamL = 0;
        double znamR = 0;

        for (User u : users) {
            double rua = matrixRepository.findByMatrixPK(new MatrixPK(u, a)).getScore();
            double rum = matrixRepository.findByMatrixPK(new MatrixPK(u, m)).getScore();

            chis += (rua - ra) * (rum - rm);
            znamL += (rua - ra) * (rua - ra);
            znamR += (rum - rm) * (rum - rm);
        }

        return chis / Math.sqrt(znamL * znamR);
    }
}
