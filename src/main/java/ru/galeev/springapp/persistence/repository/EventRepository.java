package ru.galeev.springapp.persistence.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.domain.User;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event saveAndFlush(Event event);
    List<Event> findEventsByEventManager(User eventManager);
    List<Event> findEventsByPlace(Place place);
    Event findById(Event event);

    List<Event> findAllByActiveTrue();
}
