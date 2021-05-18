package ru.galeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;
import ru.galeev.springapp.utils.DateFormatter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MatrixService matrixService;

    private static final int ACTIVE = 1;
    private static final int INACTIVE = 0;
    private static final int RATING = 5;

    public void createEvent(Event event, User user) {
        event.setActive(true);
        event.getEventManager().add(user);
        event.setRating(RATING);
        matrixService.addEventToMatrix(event);
        eventRepository.saveAndFlush(event);
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
                          Place place) {
        event.setName(name);
        event.setPlace(place);
        event.setTime(DateFormatter.fromHtmlDate(time));
        event.setCost(cost);
        event.setMinAge(minAge);
        eventRepository.saveAndFlush(event);
    }
}
