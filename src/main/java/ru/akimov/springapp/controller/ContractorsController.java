package ru.akimov.springapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akimov.springapp.persistence.domain.user.Contractor;
import ru.akimov.springapp.persistence.domain.user.User;
import ru.akimov.springapp.service.CalculationService;
import ru.akimov.springapp.service.ContractorService;
import ru.akimov.springapp.service.TechService;
import ru.akimov.springapp.service.UserService;

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

    @GetMapping("{id}")
    public String showContractor(@PathVariable("id") User user,
                                 Authentication auth,
                                 Model model) {
        model.addAllAttributes(contractorService.getResolvedTechMap(user));
        model.addAttribute("user", user);
        model.addAttribute("con", contractorService.getContractorFromUser(user));
        model.addAttribute("contracts_cnt", userService.getContracts(user).size());
        model.addAttribute("rated", contractorService.getRate(user, (User) auth.getPrincipal()));
        return "user/id";
    }

    @PostMapping("{conId}/request/calc/")
    public String createContractRequest(@PathVariable("conId") Contractor contractor,
                                        @RequestParam Map<String, String> form) {
        contractorService.createContractRequest(contractor, form.get("calcId"));
        return "redirect:/calc/" +  form.get("calcId") + "/con";
    }

    @PostMapping("{conId}/set_rating")
    public String setRating(@PathVariable("conId") Contractor contractor,
                            @RequestParam Map<String, String> form,
                            Authentication auth) {
        contractorService.setRating(contractor, (User) auth.getPrincipal(), form.get("rating"));
        return "redirect:/contractor/" + contractor.getUser().getId();
    }
}
