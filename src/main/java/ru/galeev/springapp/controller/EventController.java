package ru.galeev.springapp.controller;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;
import ru.galeev.springapp.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/{id}")
    public String getEventInfo(@PathVariable("id")Event event,
                               Authentication auth,
                               Model model) {
        model.addAttribute("event", event);
        model.addAttribute("isSub", eventService.checkRegistrationOnEvent(event, (User) auth.getPrincipal()));
        model.addAttribute("friends", eventService.getUserSubsOnEvent((User) auth.getPrincipal(), event).size());
        model.addAttribute("canEdit", false);
        return "events/id";
    }

    @GetMapping("/{id}/subs_visitors")
    public String getSubsVisitors(@PathVariable("id")Event event,
                                  Authentication auth,
                                  Model model) {
        model.addAttribute("users", eventService.getUserSubsOnEvent((User) auth.getPrincipal(), event));
        model.addAttribute("isFriendsOnEvent", true);
        model.addAttribute("event", event.getName());
        return "user/cards";
    }

    @GetMapping("/{id}/visitors")
    public String getVisitors(@PathVariable("id")Event event, Model model) {
        model.addAttribute("isOnEvent", true);
        model.addAttribute("users", event.getUserList());
        model.addAttribute("event", event);
        return "user/cards";
    }

    @PostMapping("/{id}/follow")
    public String followEvent(@PathVariable("id")Event event,
                              Authentication auth) {

        eventService.subUserOnEvent((User) auth.getPrincipal(), event);
        return "redirect:/events/" + event.getId();
    }

    @PostMapping("/{id}/unFollow")
    public String unfollowEvent(@PathVariable("id")Event event,
                              Authentication auth) {

        eventService.unSubUserOnEvent((User) auth.getPrincipal(), event);
        return "redirect:/events/" + event.getId();
    }

}
