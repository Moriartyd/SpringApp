package ru.galeev.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.service.EventService;
import ru.galeev.springapp.utils.filter.RecommendSystem;
import ru.galeev.springapp.utils.filter.contentbased.ContentBasedFilter;
import ru.galeev.springapp.utils.filter.itembased.ItemBasedFilter;

import java.util.Map;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;
    @Autowired
    RecommendSystem rSystem;
    @Autowired
    ContentBasedFilter cbFilter;
    @Autowired
    ItemBasedFilter ibFilter;

    @GetMapping
    public String main(Model model,  Authentication auth) {
        model.addAttribute("title", "Мероприятия");
        model.addAttribute("ibList", ibFilter.getRecommendedList((User) auth.getPrincipal()).keySet());
        model.addAttribute("cbList", cbFilter.getRecommendedList((User) auth.getPrincipal()).keySet());
        model.addAttribute("aList", rSystem.getRecommends((User) auth.getPrincipal()).keySet());
        return "common";
    }

    @GetMapping("/{id}")
    public String getEventInfo(@PathVariable("id")Event event,
                               Authentication auth,
                               Model model) {
        model.addAttribute("event", event);
        model.addAttribute("isSub", eventService.checkRegistrationOnEvent(event, (User) auth.getPrincipal()));
        model.addAttribute("friends", eventService.getUserSubsOnEvent((User) auth.getPrincipal(), event).size());
        model.addAttribute("rated", eventService.isRatedByUser(event, (User) auth.getPrincipal()));
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

    @PostMapping("/{id}/set_rating")
    public String setRating(@PathVariable("id")Event event,
                            @RequestParam Map<String, String> form,
                            Authentication auth) {
        eventService.setRating(event, (User) auth.getPrincipal(), Integer.parseInt(form.get("rating")));
        return "redirect:/events/" + event.getId();
    }

}
