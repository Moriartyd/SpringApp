package ru.akimov.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akimov.springapp.persistence.domain.user.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);
    User findUserByLogin(String login);
    User saveAndFlush(User user);
    User findUserById(long id);
    List<User> findAllByRoleEqualsAndActiveIsTrue(String role);
}