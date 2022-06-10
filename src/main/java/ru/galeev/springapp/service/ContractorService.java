package ru.galeev.springapp.service;

import org.springframework.stereotype.Service;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.persistence.domain.tech.*;
import ru.galeev.springapp.persistence.domain.user.Contractor;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.ContractRequestRepository;
import ru.galeev.springapp.persistence.repository.ContractorRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContractorService {

    private final ContractorRepository contractorRepository;
    private final ContractRequestRepository contractRequestRepository;
    private final UserRepository userRepository;
    private final TechService techService;

    public ContractorService(ContractorRepository contractorRepository,
                             ContractRequestRepository contractRequestRepository,
                             UserRepository userRepository,
                             TechService techService) {
        this.contractorRepository = contractorRepository;
        this.contractRequestRepository = contractRequestRepository;
        this.userRepository = userRepository;
        this.techService = techService;
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

        return contractorRepository.saveAndFlush(contractor);
    }
}
