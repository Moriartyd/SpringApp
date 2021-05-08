package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/{id}")
    public String getEventInfo(@PathVariable("id")Event event, Model model) {
        model.addAttribute("event", event);
        return "events/id";
    }

}
