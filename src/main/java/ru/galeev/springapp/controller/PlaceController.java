package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.PlaceRepository;

@Controller
@RequestMapping("place")
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    EventRepository eventRepository;

    @GetMapping
    public String main(Model model) {
        model.addAttribute("title", "Места");
        return "common";
    }

    @GetMapping("/{id}")
    public String getPlaceInfo(@PathVariable("id")Place place, Model model) {
        model.addAttribute("place", place);
        model.addAttribute("event_cnt", eventRepository.findEventsByPlace(place).size());
        return "place/id";
    }

    @GetMapping("/{id}/events")
    public String getEvents(@PathVariable("id")Place place, Model model) {
        model.addAttribute("isOnPlace", true);
        model.addAttribute("events", eventRepository.findEventsByPlace(place).isEmpty() ?
                null : eventRepository.findEventsByPlace(place));
        model.addAttribute("place", place);
        return "events/cards";
    }
}
