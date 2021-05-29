package ru.galeev.springapp.utils.filter.contentbased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.enums.EventType;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.MatrixRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContentBasedFilter {

    @Autowired
    MatrixRepository matrixRepository;
    @Autowired
    EventRepository eventRepository;

    public LinkedHashMap<Event, Double> getRecommendedList(User u) {
        List<Event> eventList = matrixRepository.getUnscoredByUser(u);
        Set<EventType> userKeyWords = u.getKeywords();

        Map<Event, Double> map = new LinkedHashMap<>();
        for (Event e : eventList) {
            map.put(e, getDice(userKeyWords, e.getKeywords()));
        }
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public double getDice(Set<EventType> u, Set<EventType> e) {
        int union = 0;

        for (EventType t : u) {
            if (e.contains(t)) {
                union++;
            }
        }
        return Math.abs((double) union)/Math.abs(EventType.values().length);
    }
}
