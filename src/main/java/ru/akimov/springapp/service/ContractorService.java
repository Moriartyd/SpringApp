package ru.akimov.springapp.service;

import org.springframework.stereotype.Service;
import ru.akimov.springapp.enums.Role;
import ru.akimov.springapp.persistence.domain.Calculation;
import ru.akimov.springapp.persistence.domain.ContractRequest;
import ru.akimov.springapp.persistence.domain.Rating;
import ru.akimov.springapp.persistence.domain.RatingPK;
import ru.akimov.springapp.persistence.domain.tech.*;
import ru.akimov.springapp.persistence.domain.user.Contractor;
import ru.akimov.springapp.persistence.domain.user.User;
import ru.akimov.springapp.persistence.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContractorService {

    private final ContractorRepository contractorRepository;
    private final ContractRequestRepository contractRequestRepository;
    private final UserRepository userRepository;
    private final TechService techService;
    private final CalculationRepository calculationRepository;
    private final RatingRepository ratingRepository;

    public ContractorService(ContractorRepository contractorRepository,
                             ContractRequestRepository contractRequestRepository,
                             UserRepository userRepository,
                             TechService techService,
                             CalculationRepository calculationRepository,
                             RatingRepository ratingRepository) {
        this.contractorRepository = contractorRepository;
        this.contractRequestRepository = contractRequestRepository;
        this.userRepository = userRepository;
        this.techService = techService;
        this.calculationRepository = calculationRepository;
        this.ratingRepository = ratingRepository;
    }


    public User createUser(User user) {
        User userFromDb = userRepository.findUserByLogin(user.getLogin());
        if (userFromDb != null) {
            return null;
        }
        user.setRole(Role.OUTSTAFF.getAuthority());
        return userRepository.saveAndFlush(user);
    }

    public Contractor createContractor(Contractor contractor, Map<String, String> form, User user) {
        Map<String, TechCarrierWall> techCarrierWalls = techService.getAllTechCarrierWall().stream()
                .collect(Collectors.toMap(TechCarrierWall::getWord, tech -> tech));
        StringBuilder techCarrierWall = new StringBuilder();
        for (String key : form.keySet()) {
            if (techCarrierWalls.get(key) != null) {
                techCarrierWall.append(techCarrierWalls.get(key).getWord()).append(";");
            }
        }
        contractor.setTechCarrierWall(techCarrierWall.toString());

        Map<String, TechExterior> techExteriors = techService.getAllTechExterior().stream()
                .collect(Collectors.toMap(TechExterior::getWord, tech -> tech));
        StringBuilder techExterior = new StringBuilder();
        for (String key : form.keySet()) {
            if (techExteriors.get(key) != null) {
                techExterior.append(techExteriors.get(key).getWord()).append(";");
            }
        }
        contractor.setTechExterior(techExterior.toString());

        Map<String, TechFoundation> techFoundations = techService.getAllTechFoundation().stream()
                .collect(Collectors.toMap(TechFoundation::getWord, tech -> tech));
        StringBuilder techFoundation = new StringBuilder();
        for (String key : form.keySet()) {
            if (techFoundations.get(key) != null) {
                techFoundation.append(techFoundations.get(key).getWord()).append(";");
            }
        }
        contractor.setTechFoundation(techFoundation.toString());

        Map<String, TechOverlaps> techOverlapss = techService.getAllTechOverlaps().stream()
                .collect(Collectors.toMap(TechOverlaps::getWord, tech -> tech));
        StringBuilder techOverlaps = new StringBuilder();
        for (String key : form.keySet()) {
            if (techOverlapss.get(key) != null) {
                techOverlaps.append(techOverlapss.get(key).getWord()).append(";");
            }
        }
        contractor.setTechOverlaps(techOverlaps.toString());

        Map<String, TechRoof> techRoofs = techService.getAllTechRoof().stream()
                .collect(Collectors.toMap(TechRoof::getWord, tech -> tech));
        StringBuilder techRoof = new StringBuilder();
        for (String key : form.keySet()) {
            if (techRoofs.get(key) != null) {
                techRoof.append(techRoofs.get(key).getWord()).append(";");
            }
        }
        contractor.setTechRoof(techRoof.toString());

        Map<String, TechLadder> techLadders = techService.getAllTechLadder().stream()
                .collect(Collectors.toMap(TechLadder::getWord, tech -> tech));
        StringBuilder techLadder = new StringBuilder();
        for (String key : form.keySet()) {
            if (techLadders.get(key) != null) {
                techLadder.append(techLadders.get(key).getWord()).append(";");
            }
        }
        contractor.setTechLadder(techLadder.toString());

        Map<String, TechInterior> techInteriors = techService.getAllTechInterior().stream()
                .collect(Collectors.toMap(TechInterior::getWord, tech -> tech));
        StringBuilder techInterior = new StringBuilder();
        for (String key : form.keySet()) {
            if (techInteriors.get(key) != null) {
                techInterior.append(techInteriors.get(key).getWord()).append(";");
            }
        }
        contractor.setTechInterior(techInterior.toString());

        contractor.setUser(user);
        Contractor createdContractor = contractorRepository.saveAndFlush(contractor);
        List<User> userList = userRepository.findAll();

        for (User u : userList) {
            Rating r = new Rating();
            r.setRating(0);
            r.setRatingPK(new RatingPK(u, createdContractor));
            ratingRepository.saveAndFlush(r);
        }

        return createdContractor;
    }

    public Map<String, String> getResolvedTechMap(User user) {
        Contractor contractor = contractorRepository.findByUser(user);
        if (contractor != null) {
            Map<String, String> techMap = new HashMap<>();

            String techCarrierWall = contractor.getTechCarrierWall().replace(";", ", ");
            String techExterior = contractor.getTechExterior().replace(";", ", ");
            String techFoundation = contractor.getTechFoundation().replace(";", ", ");
            String techOverlaps = contractor.getTechOverlaps().replace(";", ", ");
            String techRoof = contractor.getTechRoof().replace(";", ", ");
            String techLadder = contractor.getTechLadder().replace(";", ", ");
            String techInterior = contractor.getTechInterior().replace(";", ", ");

            techMap.put("techCarrierWall", techCarrierWall.substring(0, techCarrierWall.length() - 2));
            techMap.put("techExterior", techExterior.substring(0, techExterior.length() - 2));
            techMap.put("techFoundation", techFoundation.substring(0, techFoundation.length() - 2));
            techMap.put("techOverlaps", techOverlaps.substring(0, techOverlaps.length() - 2));
            techMap.put("techRoof", techRoof.substring(0, techRoof.length() - 2));
            techMap.put("techLadder", techLadder.substring(0, techLadder.length() - 2));
            techMap.put("techInterior", techInterior.substring(0, techInterior.length() - 2));
            return techMap;
        }
        return null;
    }

    public void createContractRequest(Contractor contractor, String calcId) {
        ContractRequest contractRequest = new ContractRequest();
        Calculation calculation = calculationRepository.findById(Long.valueOf(calcId)).get();

        contractRequest.setCalculation(calculation);
        contractRequest.setContractor(contractor);
        contractRequest.setUser(calculation.getUser());
        contractRequest.setStatusContractor(0);
        contractRequest.setStatusUser(1);

        contractRequestRepository.saveAndFlush(contractRequest);
    }

    public void acceptContract(ContractRequest contractRequest) {
        contractRequest.setStatusContractor(1);
        contractRequestRepository.saveAndFlush(contractRequest);
    }

    public void rejectContract(ContractRequest contractRequest) {
        contractRequest.setStatusContractor(-1);
        contractRequestRepository.saveAndFlush(contractRequest);
    }

    public Integer getRate(User con, User user) {
        if (!con.getId().equals(user.getId())) {
            if (con.getRole().equals(Role.OUTSTAFF.getAuthority())) {
                return ratingRepository.findByRatingPK(new RatingPK(user, contractorRepository.findByUser(con))).getRating();
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public Contractor getContractorFromUser(User user) {
        return contractorRepository.findByUser(user);
    }

    public void setRating(Contractor contractor, User user, String rating) {
        Rating r = ratingRepository.findByRatingPK(new RatingPK(user, contractor));
        contractor.setRatingNum(contractor.getRatingNum() + 1);
        contractor.setRating(contractor.getRating() + Integer.valueOf(rating));
        r.setRating(Integer.valueOf(rating));
        ratingRepository.saveAndFlush(r);
    }
}
