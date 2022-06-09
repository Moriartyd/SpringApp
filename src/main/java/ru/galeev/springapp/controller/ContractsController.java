package ru.galeev.springapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.service.CalculationService;

@org.springframework.stereotype.Controller
@RequestMapping("/contracts")
public class ContractsController {

    private final CalculationService calculationService;

    public ContractsController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping
    public String main(Model model,  Authentication auth) {
        model.addAttribute("title", "Расчерты");
        model.addAttribute("calcs", calculationService.getCalcs((User)auth.getPrincipal()));
        model.addAttribute("user", (User)auth.getPrincipal());
        model.addAttribute("isContractor", ((User)auth.getPrincipal()).getRole().equals(Role.OUTSTAFF.getAuthority()));
        return "common";
    }

//    @GetMapping("/{id}")
//    public String getCalcInfo(@PathVariable("id") Calculation event,
//                               Authentication auth,
//                               Model model) {
//        model.addAttribute("event", event);
//        model.addAttribute("isSub", eventService.checkRegistrationOnEvent(event, (User) auth.getPrincipal()));
//        model.addAttribute("friends", eventService.getUserSubsOnEvent((User) auth.getPrincipal(), event).size());
//        model.addAttribute("rated", eventService.isRatedByUser(event, (User) auth.getPrincipal()));
//        model.addAttribute("canEdit", false);
//        return "events/id";
//    }

//    @GetMapping("/{id}/subs_visitors")
//    public String getSubsVisitors(@PathVariable("id") Calculation event,
//                                  Authentication auth,
//                                  Model model) {
//        model.addAttribute("users", eventService.getUserSubsOnEvent((User) auth.getPrincipal(), event));
//        model.addAttribute("isFriendsOnEvent", true);
//        model.addAttribute("event", event.getName());
//        return "user/cards";
//    }

//    @GetMapping("/{id}/visitors")
//    public String getVisitors(@PathVariable("id") Calculation event, Model model) {
//        model.addAttribute("isOnEvent", true);
//        model.addAttribute("users", event.getUserList());
//        model.addAttribute("event", event);
//        return "user/cards";
//    }

//    @PostMapping("/{id}/follow")
//    public String followEvent(@PathVariable("id") Calculation event,
//                              Authentication auth) {
//
//        eventService.subUserOnEvent((User) auth.getPrincipal(), event);
//        return "redirect:/events/" + event.getId();
//    }

//    @PostMapping("/{id}/unFollow")
//    public String unfollowEvent(@PathVariable("id") Calculation event,
//                              Authentication auth) {
//
//        eventService.unSubUserOnEvent((User) auth.getPrincipal(), event);
//        return "redirect:/events/" + event.getId();
//    }

//    @PostMapping("/{id}/set_rating")
//    public String setRating(@PathVariable("id") Calculation event,
//                            @RequestParam Map<String, String> form,
//                            Authentication auth) {
//        eventService.setRating(event, (User) auth.getPrincipal(), Integer.parseInt(form.get("rating")));
//        return "redirect:/events/" + event.getId();
//    }

}
