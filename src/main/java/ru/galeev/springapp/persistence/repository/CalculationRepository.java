package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.user.User;

import java.util.List;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    Calculation saveAndFlush(Calculation calculation);
    Calculation findById(Calculation event);
    List<Calculation> findAllByUserId(User user);

    List<Calculation> findAllByActiveTrue();
}
