package ru.galeev.springapp.utils.filter.contentbased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.enums.EventType;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.CalculationRepository;
import ru.galeev.springapp.persistence.repository.ContractRequestRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContentBasedFilter {

    @Autowired
    ContractRequestRepository contractRequestRepository;
    @Autowired
    CalculationRepository calculationRepository;

    public LinkedHashMap<Calculation, Double> getRecommendedList(User u) {
        List<Calculation> eventList = contractRequestRepository.getUnscoredByUser(u);
        Set<EventType> userKeyWords = u.getKeywords();

        Map<Calculation, Double> map = new LinkedHashMap<>();
        for (Calculation e : eventList) {
            double dice = getDice(userKeyWords, e.getKeywords());
            if (dice != 0 && map.size() < 6) {
                map.put(e, dice);
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

    private double getDice(Set<EventType> u, Set<EventType> e) {
        int union = 0;

        for (EventType t : u) {
            if (e.contains(t)) {
                union++;
            }
        }
        return Math.abs(2 * (double) union)/Math.abs(EventType.values().length);
    }
}
