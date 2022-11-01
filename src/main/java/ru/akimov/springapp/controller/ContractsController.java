package ru.akimov.springapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akimov.springapp.persistence.domain.ContractRequest;
import ru.akimov.springapp.persistence.domain.user.User;
import ru.akimov.springapp.service.CalculationService;
import ru.akimov.springapp.service.ContractorService;
import ru.akimov.springapp.service.TechService;
import ru.akimov.springapp.service.UserService;

@org.springframework.stereotype.Controller
@RequestMapping("/contract")
public class ContractsController {

    private final CalculationService calculationService;
    private final TechService techService;
    private final UserService userService;
    private final ContractorService contractorService;

    public ContractsController(CalculationService calculationService,
                               TechService techService, UserService userService, ContractorService contractorService) {
        this.calculationService = calculationService;
        this.techService = techService;
        this.userService = userService;
        this.contractorService = contractorService;
    }

    @GetMapping("my_contracts")
    public String showMyContracts(Model model,  Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("isContracts", true);
        model.addAttribute("contracts", userService.getContracts(user));
        model.addAttribute("user", user);
        return "user/cards";
    }

    @GetMapping("my_contract_rq")
    public String showContractRequests(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("conList", userService.getContractRq(user));
        model.addAttribute("user", user);
        return "contracts/contractRqList";
    }

    @PostMapping("{id}/accept")
    public String acceptContract( @PathVariable("id") ContractRequest contractRequest,
                                  Model model) {
        contractorService.acceptContract(contractRequest);
        return "redirect:/contract/my_contract_rq";
    }

    @PostMapping("{id}/reject")
    public String rejectContract( @PathVariable("id") ContractRequest contractRequest,
                                  Model model) {
        contractorService.rejectContract(contractRequest);
        return "redirect:/contract/my_contract_rq";
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
