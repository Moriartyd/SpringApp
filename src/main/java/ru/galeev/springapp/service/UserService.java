package ru.galeev.springapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.enums.Role;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.ContractRequest;
import ru.galeev.springapp.persistence.domain.user.Contractor;
import ru.galeev.springapp.persistence.domain.user.User;
import ru.galeev.springapp.persistence.repository.CalculationRepository;
import ru.galeev.springapp.persistence.repository.ContractRequestRepository;
import ru.galeev.springapp.persistence.repository.ContractorRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service("userDetailService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ContractRequestRepository contractRequestRepository;
    private final ContractorRepository contractorRepository;
    private final CalculationRepository calculationRepository;

    private final static String link = "http://192.168.1.70:8090";

    public UserService(UserRepository userRepository,
                       ContractRequestRepository contractRequestRepository,
                       ContractorRepository contractorRepository,
                       CalculationRepository calculationRepository) {
        this.userRepository = userRepository;
        this.contractRequestRepository = contractRequestRepository;
        this.contractorRepository = contractorRepository;
        this.calculationRepository = calculationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(s);
    }

    public boolean createUser(User user, Map<String, String> form) {
        User userFromDb = userRepository.findUserByLogin(user.getLogin());
        if (userFromDb != null) {
            return false;
        }
//        user.setRole(Role.USER.getAuthority());
//        user.setActivationCode(UUID.randomUUID().toString());
        user.setRole(Role.USER.getAuthority());

        userRepository.saveAndFlush(user);
        return true;
    }

//    public boolean activateUser(String code) {
//        User user = userRepository.findByActivationCode(code);
//        if (user == null) {
//            return false;
//        }
//        user.setActivationCode(null);
//        user.setActive(true);
//        userRepository.saveAndFlush(user);
//        return true;
//    }

//    public void followUser(User obj, User user) {
//        obj.getFollowers().add(user);
//        userRepository.saveAndFlush(obj);
//    }

//    public void unFollowUser(User obj, User user) {
//        obj.getFollowers().remove(user);
//        userRepository.saveAndFlush(obj);
//    }

    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        userList.sort(User.COMPARE_BY_ID);
        return userList;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
//        userRepository.saveAndFlush(user);
    }

    public void editUser(User user, String login, Map<String, String> form) {
        user.setLogin(login);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.setRole(Role.valueOf(key).getAuthority());
            }
        }
        userRepository.saveAndFlush(user);
    }

    public void editUser(User user,
                         String name,
                         String surname,
                         int age,
                         String password,
                         String email,
                         Map<String, String> form) {
        if (!name.isEmpty()) {
            user.setName(name);
        }
        if (!surname.isEmpty()) {
            user.setSurname(surname);
        }
        if (age != user.getAge()) {
            user.setAge(age);
        }
        if (!password.isEmpty()) {
            user.setPassword(password);
        }
        if (!email.isEmpty()) {
            user.setEmail(email);
        }
//        Set<String> types = Arrays.stream(EventType.values())
//                .map(EventType::name)
//                .collect(Collectors.toSet());
//        for (String key : form.keySet()) {
//            if (types.contains(key)) {
//                user.getKeywords().add(EventType.valueOf(key));
//            }
//        }
        userRepository.saveAndFlush(user);
    }

    public List<ContractRequest> getContracts(User user) {
        if (user.getRole().equals(Role.OUTSTAFF)) {
            Contractor contractor = contractorRepository.findByUser(user);
            if (contractor != null) {
                return contractRequestRepository.findByContractorAccepted(contractor, 1);
            } else {
                return null;
            }
        } else {
            return contractRequestRepository.findByUser(user);
        }
    }

    public List<Calculation> getCalculations(User user) {
        return calculationRepository.findByUser(user);
    }
}
