package ru.galeev.springapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.user.Contractor;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.service.CalculationService;
import ru.galeev.springapp.service.ContractorService;
import ru.galeev.springapp.service.TechService;
import ru.galeev.springapp.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/contractor")
public class ContractorsController {

    private final CalculationService calculationService;
    private final TechService techService;
    private final UserService userService;
    private final ContractorService contractorService;

    public ContractorsController(CalculationService calculationService,
                                 TechService techService,
                                 UserService userService,
                                 ContractorService contractorService) {
        this.calculationService = calculationService;
        this.techService = techService;
        this.userService = userService;
        this.contractorService = contractorService;
    }

    @GetMapping("/registrationU")
    public String getRegistrationUForm(Model model) {
        return "contractors/registrationU";
    }

    @PostMapping("/registrationU")
    public String registerAsUser(User user,
                               Model model) {
        User createdUser = contractorService.createUser(user);
        if (createdUser == null) {
            model.addAttribute("message", "Пользователь уже существует");
            return "contractors/registrationU";
        }
        model.addAttribute("user", createdUser);
        return "redirect:/contractor/registration/" + createdUser.getId();
    }

    @GetMapping("/registration/{id}")
    public String getRegistrationForm(Model model,
                                      @PathVariable("id") User user) {
        model.addAttribute("techCarrierWalls", techService.getAllTechCarrierWall());
        model.addAttribute("techExteriors", techService.getAllTechExterior());
        model.addAttribute("techFoundations", techService.getAllTechFoundation());
        model.addAttribute("techOverlapss", techService.getAllTechOverlaps());
        model.addAttribute("techRoofs", techService.getAllTechRoof());
        model.addAttribute("techLadders", techService.getAllTechLadder());
        model.addAttribute("techInteriors", techService.getAllTechInterior());
        model.addAttribute("user", user);
        return "contractors/registration";
    }

    @PostMapping("/registration/{id}")
    public String registerAsContractor(@PathVariable("id") User user,
                                       Contractor contractor,
                                       @RequestParam Map<String, String> form,
                                       Model model) {
//        if (!contractorService.createUser(user)) {
//            model.addAttribute("message", "Пользователь уже существует");
//            return "contractors/registrationU";
//        }
        contractorService.createContractor(contractor, form, user);

        return "redirect:/login";
    }
}
