//package ru.galeev.springapp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.galeev.springapp.persistence.domain.Calculation;
//import ru.galeev.springapp.persistence.domain.ContractRequest;
//import ru.galeev.springapp.persistence.domain.user.User;
//import ru.galeev.springapp.persistence.repository.CalculationRepository;
//import ru.galeev.springapp.persistence.repository.ContractRequestRepository;
//import ru.galeev.springapp.persistence.repository.UserRepository;
//
//import java.util.List;
//
//@Service
//public class MatrixService {
//    @Autowired
//    CalculationRepository calculationRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    ContractRequestRepository contractRequestRepository;
//
//    public void addUserToMatrix(User user) {
//        List<Calculation> eventList = calculationRepository.findAllByActiveTrue();
//        for (Calculation e : eventList) {
//            ContractRequest m = new ContractRequest(new MatrixPK(user, e));
//            m.setFilteredScore(0);
//            m.setScore(0);
//            contractRequestRepository.save(m);
//        }
//    }
//
//    public void addEventToMatrix(Calculation event) {
//        List<User> userList = userRepository.findAllByActiveTrue();
//        for (User u : userList) {
//            ContractRequest m = new ContractRequest(new MatrixPK(u, event));
//            m.setFilteredScore(0);
//            m.setScore(0);
//            contractRequestRepository.save(m);
//        }
//    }
//
////    public List<Matrix> getU(Event a, Event b) {
////
////    }
//}
