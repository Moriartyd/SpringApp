package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.repository.PlaceRepository;

@Controller
@RequestMapping("place")
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @GetMapping("/{id}")
    public String getPlaceInfo(@PathVariable("id")Place place, Model model) {
        model.addAttribute("place", place);
        return "place/id";
    }
}
