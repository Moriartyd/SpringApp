package ru.akimov.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akimov.springapp.persistence.domain.Calculation;
import ru.akimov.springapp.persistence.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    Calculation saveAndFlush(Calculation calculation);
    Calculation findCalculationById(Long id);
    Optional<Calculation> findById(Long id);
    List<Calculation> findByUser(User user);

    List<Calculation> findAllByActiveTrue();
}
