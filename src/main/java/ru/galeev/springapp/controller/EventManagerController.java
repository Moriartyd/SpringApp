package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.PlaceRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;
import ru.galeev.springapp.utils.DateFormatter;
import ru.galeev.springapp.utils.ListUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/events/managing")
@PreAuthorize("hasAuthority('MANAGER')")
public class EventManagerController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("places", placeRepository.findAll());
        return "events/registration";
    }

    @PostMapping("/registration")
    public String createEvent(Event event, Model model) {
        event.setActive(1);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        event.getEventManager().add(user);
        eventRepository.saveAndFlush(event);
        return "redirect:/events/managing/my_events";
    }

    @GetMapping("/my_events")
    public String getEventList(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Event> eventList = eventRepository.findEventsByEventManager(user);
        model.addAttribute("eventList", eventList);
        return "events/eventList";
    }

    @Transient
    @GetMapping("event/{id}")
    public String getOneEvent(@PathVariable("id") Event event,
                              Authentication auth,
                              Model model) {
//        User user = userRepository.findUserByEventList(event);
        model.addAttribute("canEdit",event.getEventManager().contains(((User) auth.getPrincipal())));
        model.addAttribute("event", event);
        model.addAttribute("time", DateFormatter.getHtmlDate(event.getTime()));
        model.addAttribute("places", placeRepository.findAll());
        return "events/id";
    }

    @PostMapping("event/{id}/delete")
    public String deleteEvent(@PathVariable("id") Event event) {
        event.setActive(0);
        eventRepository.saveAndFlush(event);
        return "redirect:/events/managing/my_events";
    }

    @PostMapping("event/{id}")
    public String editEvent(@PathVariable("id") Event event,
                            @RequestParam("name") String name,
                            @RequestParam("time") String time,
                            @RequestParam("cost") int cost,
                            @RequestParam("minAge") int minAge) {
        event.setName(name);
        event.setTime(DateFormatter.fromHtmlDate(time));
        event.setCost(cost);
        event.setMinAge(minAge);
        eventRepository.saveAndFlush(event);
        return "redirect:/events/managing/my_events";
    }
}
