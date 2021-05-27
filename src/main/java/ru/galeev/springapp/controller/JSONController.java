package ru.galeev.springapp.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.PlaceRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/json")
public class JSONController {

    @Autowired
    @Qualifier("MyGson")
    Gson gson;

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    PlaceRepository placeRepository;

    @ResponseBody
    @GetMapping("/users/{id}")
    public String getById(@PathVariable("id") User user) {
        return gson.toJson(user);
    }

    @ResponseBody
    @GetMapping("/users")
    public String getUsers() {
        List<User> userList = userRepository.findAllByRoleEqualsAndActiveIsTrue(Role.USER.getAuthority());

        return gson.toJson(userList);
    }

    @ResponseBody
    @GetMapping("/events")
    public String getEvents() {
        List<Event> eventList = eventRepository.findAllByActiveTrue();

        return gson.toJson(eventList);
    }

    @ResponseBody
    @GetMapping("/places")
    public String getPlaces() {
        List<Place> placeList = placeRepository.findAll();

        return gson.toJson(placeList);
    }
}
