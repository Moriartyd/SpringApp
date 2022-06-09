package ru.galeev.springapp.service;

import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.domain.*;
import ru.galeev.springapp.persistence.domain.user.Contractor;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.CalculationRepository;
import ru.galeev.springapp.persistence.repository.ContractRequestRepository;
import ru.galeev.springapp.persistence.repository.ContractorRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.*;

@Service
public class CalculationService {

    private final CalculationRepository calculationRepository;
    private final UserRepository userRepository;
    private final ContractRequestRepository contractRequestRepository;
    private final TechService techService;
    private final ContractorRepository contractorRepository;

    private static final int ACTIVE = 1;
    private static final int INACTIVE = 0;
    private static final int RATING = 5;

    public CalculationService(CalculationRepository calculationRepository,
                              UserRepository userRepository,
                              ContractRequestRepository contractRequestRepository,
                              TechService techService, ContractorRepository contractorRepository) {
        this.calculationRepository = calculationRepository;
        this.userRepository = userRepository;
        this.contractRequestRepository = contractRequestRepository;
        this.techService = techService;
        this.contractorRepository = contractorRepository;
    }

//    public void createEvent(Calculation event, User user, Map<String, String> form) {
//        Set<String> types = Arrays.stream(EventType.values())
//                .map(EventType::name)
//                .collect(Collectors.toSet());
//        for (String key : form.keySet()) {
//            if (types.contains(key)) {
//                event.getKeywords().add(EventType.valueOf(key));
//            }
//        }
//        event.setActive(true);
//        event.getEventManager().add(user);
//        event.setRating(RATING);
//        calculationRepository.saveAndFlush(event);
//        matrixService.addEventToMatrix(event);
//
//    }
    public void archiveEvent(Calculation event) {
//        event.setActive(INACTIVE);
//        eventRepository.saveAndFlush(event);
        calculationRepository.delete(event);
    }

//    public void editEvent(Calculation event,
//                          String name,
//                          String time,
//                          int cost,
//                          int minAge,
//                          Place place,
//                          Map<String, String> form) {
//        event.setName(name);
//        event.setPlace(place);
//        event.setTime(DateFormatter.fromHtmlDate(time));
//        event.setCost(cost);
//        event.setMinAge(minAge);
//        event.getKeywords().clear();
//        Set<String> types = Arrays.stream(EventType.values())
//                .map(EventType::name)
//                .collect(Collectors.toSet());
//        for (String key : form.keySet()) {
//            if (types.contains(key)) {
//                event.getKeywords().add(EventType.valueOf(key));
//            }
//        }
//        calculationRepository.saveAndFlush(event);
//    }

    public Object getCalcs(User user) {
        return calculationRepository.findByUser(user);
    }

    public void createCalc(Calculation calculation, User principal, Map<String, String> form) {
        calculation.setUser(principal);
        calculation.setActive(true);
        calculation.setCost(new Random().nextInt());
        calculationRepository.saveAndFlush(calculation);

    }

    public Boolean checkForEditPossibility(Calculation calc, User user) {
        return calc.getUser().getId().equals(user.getId());
    }

    public List<Contractor> findContractors(Calculation calc) {
        return contractorRepository.findAll();
    }

//    public Map<String, String> getCalcTechs(Calculation calc) {
//        Map<String, String> model = new HashMap<>();
//
//        model.put("techCarrierWall", ca)
//    }
}
