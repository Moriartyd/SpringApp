package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.repository.EventRepository;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/{id}")
    public String getEventInfo(@PathVariable("id")Event event, Model model) {
        model.addAttribute("event", event);
        model.addAttribute("canEdit", false);
        return "events/id";
    }

}
