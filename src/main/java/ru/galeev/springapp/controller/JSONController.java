package ru.galeev.springapp.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.user.Contractor;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.CalculationRepository;
import ru.galeev.springapp.persistence.repository.ContractorRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/json")
public class JSONController {
    private final Gson gson;
    private final UserRepository userRepository;
    private final CalculationRepository calculationRepository;
    private final ContractorRepository contractorRepository;

    public JSONController(Gson gson,
                          UserRepository userRepository,
                          CalculationRepository calculationRepository,
                          ContractorRepository contractorRepository) {
        this.gson = gson;
        this.userRepository = userRepository;
        this.calculationRepository = calculationRepository;
        this.contractorRepository = contractorRepository;
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public String getById(@PathVariable("id") User user) {
        return gson.toJson(user);
    }

    @ResponseBody
    @GetMapping("/users")
    public String getUsers() {
        List<User> userList = userRepository.findAll();

        return gson.toJson(userList);
    }

    @ResponseBody
    @GetMapping("/calcs")
    public String getCalcs() {
        List<Calculation> calculationList = calculationRepository.findAll();
        return gson.toJson(calculationList);
    }

    @ResponseBody
    @GetMapping("/contractors")
    public String getContractors() {
        List<Contractor> contractorList = contractorRepository.findAll();

        return gson.toJson(contractorList);
    }
}
