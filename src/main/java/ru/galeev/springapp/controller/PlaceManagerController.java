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
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.CalculationRepository;

import java.util.List;

@Controller
@RequestMapping("place/managing")
@PreAuthorize("hasAuthority('PLACE')")
public class PlaceManagerController {

    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    CalculationRepository calculationRepository;

    @GetMapping("/registration")
    public String getRegistrationForm() {
        return "place/registration";
    }

    @GetMapping("/my_places")
    public String getPlaceList(Model model) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Place> placeList = placeRepository.findAllByOwner(owner);
        model.addAttribute("places", placeList);
        return "place/placeList";
    }

    @GetMapping("/{id}")
    public String getOnePlace(@PathVariable("id") Place place, Model model) {
        model.addAttribute("place", place);
        model.addAttribute("event_cnt", calculationRepository.findEventsByPlace(place).size());
        return "place/id";
    }

    @PostMapping("/registration")
    public String createPlace(Place place) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        place.setOwner(owner);
        placeRepository.saveAndFlush(place);
        return "redirect:/place/managing/my_places";
    }

    @PostMapping("{id}/delete")
    public String deletePlace(@PathVariable("id") Place place) {
        Place servicePlace = placeRepository.getServicePlace();
        List<Calculation> eventList = calculationRepository.findEventsByPlace(place);
        for (Calculation event : eventList) {
            event.setPlace(servicePlace);
            calculationRepository.saveAndFlush(event);
        }
        placeRepository.delete(place);
        return "redirect:/place/managing/my_places";
    }

}
