//package ru.galeev.springapp.controller;
//
//import com.google.gson.Gson;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import ru.galeev.springapp.enums.Role;
//import ru.galeev.springapp.persistence.domain.Calculation;
//import ru.galeev.springapp.persistence.domain.Place;
//import ru.galeev.springapp.persistence.domain.user.User;
//import ru.galeev.springapp.persistence.repository.CalculationRepository;
//import ru.galeev.springapp.persistence.repository.UserRepository;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/json")
//public class JSONController {
//    private final Gson gson;
//    private final UserRepository userRepository;
//    private final CalculationRepository calculationRepository;
//    private final PlaceRepository placeRepository;
//
//    public JSONController(Gson gson,
//                          UserRepository userRepository,
//                          CalculationRepository calculationRepository,
//                          PlaceRepository placeRepository) {
//        this.gson = gson;
//        this.userRepository = userRepository;
//        this.calculationRepository = calculationRepository;
//        this.placeRepository = placeRepository;
//    }
//
//    @ResponseBody
//    @GetMapping("/users/{id}")
//    public String getById(@PathVariable("id") User user) {
//        return gson.toJson(user);
//    }
//
//    @ResponseBody
//    @GetMapping("/users")
//    public String getUsers() {
//        List<User> userList = userRepository.findAllByRoleEqualsAndActiveIsTrue(Role.USER.getAuthority());
//
//        return gson.toJson(userList);
//    }
//
//    @ResponseBody
//    @GetMapping("/events")
//    public String getEvents() {
//        List<Calculation> eventList = calculationRepository.findAllByActiveTrue();
//
//        return gson.toJson(eventList);
//    }
//
//    @ResponseBody
//    @GetMapping("/places")
//    public String getPlaces() {
//        List<Place> placeList = placeRepository.findAll();
//
//        return gson.toJson(placeList);
//    }
//}
