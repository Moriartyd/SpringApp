package ru.galeev.springapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.service.CalculationService;
import ru.galeev.springapp.service.TechService;

import java.util.Map;

@Controller
@RequestMapping("/calc")
//@PreAuthorize("hasAuthority('OUTSTAFF')")
public class CalculationController {

    private final CalculationService calculationService;
    private final TechService techService;

    public CalculationController(CalculationService calculationService,
                                TechService techService) {
        this.calculationService = calculationService;
        this.techService = techService;
    }

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("techCarrierWalls", techService.getAllTechCarrierWall());
        model.addAttribute("techExteriors", techService.getAllTechExterior());
        model.addAttribute("techFoundations", techService.getAllTechFoundation());
        model.addAttribute("techOverlapss", techService.getAllTechOverlaps());
        model.addAttribute("techRoofs", techService.getAllTechRoof());
        model.addAttribute("techLadders", techService.getAllTechLadder());
        model.addAttribute("techInteriors", techService.getAllTechInterior());
        return "calcs/registration";
    }

    @PostMapping("/registration")
    public String createEvent(Calculation calculation,
                              @RequestParam Map<String, String> form,
                              Authentication auth) {
        calculationService.createCalc(calculation, (User) auth.getPrincipal(), form);
        return "redirect:/calc/my_calcs";
    }

    @GetMapping("/my_calcs")
    public String getEventList(Model model,
                               Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("calcs", calculationService.getCalcs(user));
        model.addAttribute("isCalcs", true);
        model.addAttribute("user", user);

        return "user/cards";
    }

//    @Transient
    @GetMapping("{id}")
    public String getOneCalc(@PathVariable("id") Calculation calc,
                              Authentication auth,
                              Model model) {
        model.addAttribute("canEdit", calculationService.checkForEditPossibility(calc, (User) auth.getPrincipal()));
        model.addAttribute("calc", calc);
//        model.addAllAttributes(calculationService.getCalcTechs(calc));
        return "calcs/id";
    }

    @GetMapping("{id}/con")
    public String getContractorsByCalc(@PathVariable("id") Calculation calc,
                             Authentication auth,
                             Model model) {
//        model.addAttribute("canEdit", calculationService.checkForEditPossibility(calc, (User) auth.getPrincipal()));
        model.addAttribute("calc", calc);
        model.addAttribute("contractors", calculationService.findContractors(calc));
        model.addAttribute("statusMap", calculationService.getStatusMap(calc));
//        model.addAttribute("contractors", calculationService.getContractorList(calc));
//        model.addAllAttributes(calculationService.getCalcTechs(calc));
        return "contractors/contractorList";
    }

//    @PostMapping("event/{id}/delete")
//    public String deleteEvent(@PathVariable("id") Calculation event) {
//        eventService.archiveEvent(event);
//        return "redirect:/contractor/managing/my_events";
//    }
//
//    @PostMapping("edit/{id}")
//    public String editEvent(@PathVariable("id") Calculation event,
//                            @RequestParam("name") String name,
//                            @RequestParam("time") String time,
//                            @RequestParam("cost") int cost,
//                            @RequestParam("minAge") int minAge,
//                            @RequestParam("place") Place place,
//                            @RequestParam Map<String, String> form) {
//        eventService.editEvent(event, name, time, cost, minAge, place, form);
//        return "redirect:/contractor/managing/my_events";
//    }
}
