package ru.galeev.springapp.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.galeev.springapp.enums.EventType;
import ru.galeev.springapp.persistence.domain.*;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.MatrixRepository;
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
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MatrixService matrixService;
    @Autowired
    MatrixRepository matrixRepository;
    @Autowired
    @Qualifier("MyGson")
    Gson gson;

    private static final int ACTIVE = 1;
    private static final int INACTIVE = 0;
    private static final int RATING = 5;

    public void createEvent(Event event, User user, Map<String, String> form) {
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
        eventRepository.saveAndFlush(event);
        matrixService.addEventToMatrix(event);

    }

    public boolean checkRegistrationOnEvent(Event event, User user) {
        return event.getUserList().contains(user);
    }

    public void subUserOnEvent(User user, Event event) {
        event.getUserList().add(user);
        event.setRegisteredVisitors(event.getRegisteredVisitors() + 1);
        eventRepository.saveAndFlush(event);
    }

    public void unSubUserOnEvent(User user, Event event) {
        event.getUserList().remove(user);
        event.setRegisteredVisitors(event.getRegisteredVisitors() - 1);
        eventRepository.saveAndFlush(event);
    }

    public List<User> getUserSubsOnEvent(User user, Event event) {
        user = userRepository.findUserById(user.getId());
        return user.getSubscriptions().stream()
                .filter(u->u.getUserRegisteredEvents().contains(event))
                .collect(Collectors.toList());
    }

    public List<Event> getEventsByManager(User user) {
        return eventRepository.findEventsByEventManager(user);
    }

    public boolean checkForEditPossibility(Event event, User user) {
        return event.getEventManager().contains(user);
    }

    public void archiveEvent(Event event) {
//        event.setActive(INACTIVE);
//        eventRepository.saveAndFlush(event);
        eventRepository.delete(event);
    }

    public void editEvent(Event event,
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
        eventRepository.saveAndFlush(event);
    }

    public void setRating(Event event, User user, int req) {
        Type type = new TypeToken<Map<String, Integer>>(){}.getType();
        event.setRating(event.getEvaluators() == 0 ? req : event.getRating() + req);
        event.setEvaluators(event.getEvaluators() + 1);
        Matrix matrix = matrixRepository.findByMatrixPK(new MatrixPK(user, event));
        matrix.setScore(req);
        eventRepository.saveAndFlush(event);
        matrixRepository.saveAndFlush(matrix);
    }

    public double isRatedByUser(Event e, User u) {
        Matrix m = matrixRepository.findByMatrixPK(new MatrixPK(u, e));
        return m.getScore();
    }
}
