package ru.galeev.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Matrix;
import ru.galeev.springapp.persistence.domain.MatrixPK;
import ru.galeev.springapp.persistence.domain.User;
import ru.galeev.springapp.persistence.repository.EventRepository;
import ru.galeev.springapp.persistence.repository.MatrixRepository;
import ru.galeev.springapp.persistence.repository.UserRepository;

import java.util.List;

@Service
public class MatrixService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MatrixRepository matrixRepository;

    public void addUserToMatrix(User user) {
        List<Event> eventList = eventRepository.findAllByActiveTrue();
        for (Event e : eventList) {
            Matrix m = new Matrix(new MatrixPK(user, e));
            matrixRepository.save(m);
        }
    }

    public void addEventToMatrix(Event event) {
        List<User> userList = userRepository.findAllByActiveTrue();
        for (User u : userList) {
            Matrix m = new Matrix(new MatrixPK(u, event));
            matrixRepository.save(m);
        }
    }
}
