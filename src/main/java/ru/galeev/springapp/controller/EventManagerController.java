package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.enums.EventType;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.service.EventService;
import ru.galeev.springapp.service.PlaceService;
import ru.galeev.springapp.utils.DateFormatter;

import java.util.Map;

@Controller
@RequestMapping("/events/managing")
@PreAuthorize("hasAuthority('MANAGER')")
public class EventManagerController {

    @Autowired
    EventService eventService;
    @Autowired
    PlaceService placeService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("places", placeService.getAllPlaces());
        model.addAttribute("types", EventType.values());
        return "events/registration";
    }

    @PostMapping("/registration")
    public String createEvent(Event event,
                              @RequestParam Map<String, String> form,
                              Authentication auth) {
        eventService.createEvent(event, (User) auth.getPrincipal(), form);
        return "redirect:/events/managing/my_events";
    }

    @GetMapping("/my_events")
    public String getEventList(Model model,
                               Authentication auth) {
        model.addAttribute("eventList", eventService.getEventsByManager((User) auth.getPrincipal()));
        return "events/eventList";
    }

//    @Transient
    @GetMapping("event/{id}")
    public String getOneEvent(@PathVariable("id") Event event,
                              Authentication auth,
                              Model model) {
        model.addAttribute("canEdit", eventService.checkForEditPossibility(event, (User) auth.getPrincipal()));
        model.addAttribute("event", event);
        model.addAttribute("time", DateFormatter.getHtmlDate(event.getTime()));
        model.addAttribute("places", placeService.getAllPlaces());
        model.addAttribute("keywords", EventType.values());
        return "events/id";
    }

    @PostMapping("event/{id}/delete")
    public String deleteEvent(@PathVariable("id") Event event) {
        eventService.archiveEvent(event);
        return "redirect:/events/managing/my_events";
    }

    @PostMapping("event/{id}")
    public String editEvent(@PathVariable("id") Event event,
                            @RequestParam("name") String name,
                            @RequestParam("time") String time,
                            @RequestParam("cost") int cost,
                            @RequestParam("minAge") int minAge,
                            @RequestParam("place") Place place,
                            @RequestParam Map<String, String> form) {
        eventService.editEvent(event, name, time, cost, minAge, place, form);
        return "redirect:/events/managing/my_events";
    }
}
