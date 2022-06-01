package ru.galeev.springapp.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.enums.EventType;
import ru.galeev.springapp.persistence.domain.*;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.CalculationRepository;
import ru.galeev.springapp.persistence.repository.ContractRequestRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;
import ru.galeev.springapp.utils.DateFormatter;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    CalculationRepository calculationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MatrixService matrixService;
    @Autowired
    ContractRequestRepository contractRequestRepository;
    @Autowired
    @Qualifier("MyGson")
    Gson gson;

    private static final int ACTIVE = 1;
    private static final int INACTIVE = 0;
    private static final int RATING = 5;

    public void createEvent(Calculation event, User user, Map<String, String> form) {
        Set<String> types = Arrays.stream(EventType.values())
                .map(EventType::name)
                .collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (types.contains(key)) {
                event.getKeywords().add(EventType.valueOf(key));
            }
        }
        event.setActive(true);
        event.getEventManager().add(user);
        event.setRating(RATING);
        calculationRepository.saveAndFlush(event);
        matrixService.addEventToMatrix(event);

    }

    public boolean checkRegistrationOnEvent(Calculation event, User user) {
        return event.getUserList().contains(user);
    }

    public void subUserOnEvent(User user, Calculation event) {
        event.getUserList().add(user);
        event.setRegisteredVisitors(event.getRegisteredVisitors() + 1);
        calculationRepository.saveAndFlush(event);
    }

    public void unSubUserOnEvent(User user, Calculation event) {
        event.getUserList().remove(user);
        event.setRegisteredVisitors(event.getRegisteredVisitors() - 1);
        calculationRepository.saveAndFlush(event);
    }

    public List<User> getUserSubsOnEvent(User user, Calculation event) {
        user = userRepository.findUserById(user.getId());
        return user.getSubscriptions().stream()
                .filter(u->u.getUserRegisteredEvents().contains(event))
                .collect(Collectors.toList());
    }

    public List<Calculation> getEventsByManager(User user) {
        return calculationRepository.findEventsByEventManager(user);
    }

    public boolean checkForEditPossibility(Calculation event, User user) {
        return event.getEventManager().contains(user);
    }

    public void archiveEvent(Calculation event) {
//        event.setActive(INACTIVE);
//        eventRepository.saveAndFlush(event);
        calculationRepository.delete(event);
    }

    public void editEvent(Calculation event,
                          String name,
                          String time,
                          int cost,
                          int minAge,
                          Place place,
                          Map<String, String> form) {
        event.setName(name);
        event.setPlace(place);
        event.setTime(DateFormatter.fromHtmlDate(time));
        event.setCost(cost);
        event.setMinAge(minAge);
        event.getKeywords().clear();
        Set<String> types = Arrays.stream(EventType.values())
                .map(EventType::name)
                .collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (types.contains(key)) {
                event.getKeywords().add(EventType.valueOf(key));
            }
        }
        calculationRepository.saveAndFlush(event);
    }

    public void setRating(Calculation event, User user, int req) {
        Type type = new TypeToken<Map<String, Integer>>(){}.getType();
        event.setRating(event.getEvaluators() == 0 ? req : event.getRating() + req);
        event.setEvaluators(event.getEvaluators() + 1);
        ContractRequest matrix = contractRequestRepository.findByMatrixPK(new MatrixPK(user, event));
        matrix.setScore(req);
        calculationRepository.saveAndFlush(event);
        contractRequestRepository.saveAndFlush(matrix);
    }

    public double isRatedByUser(Calculation e, User u) {
        ContractRequest m = contractRequestRepository.findByMatrixPK(new MatrixPK(u, e));
        return m.getScore();
    }
}
