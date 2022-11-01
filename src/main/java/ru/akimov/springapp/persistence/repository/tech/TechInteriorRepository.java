package ru.akimov.springapp.persistence.repository.tech;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akimov.springapp.persistence.domain.tech.TechInterior;

public interface TechInteriorRepository extends JpaRepository<TechInterior, Long> {
}
