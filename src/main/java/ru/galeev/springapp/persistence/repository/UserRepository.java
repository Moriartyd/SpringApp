package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);
    User findUserByLogin(String login);
    User saveAndFlush(User user);
    User findUserById(long id);
    User findByActivationCode(String code);

    User findUserByEventList(Event event);
}
