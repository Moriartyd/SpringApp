package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.Calculation;

import java.util.List;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    Calculation saveAndFlush(Calculation event);
    Calculation findById(Calculation event);

    List<Calculation> findAllByActiveTrue();
}
