package ru.akimov.springapp.persistence.repository.tech;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akimov.springapp.persistence.domain.tech.TechRoof;

public interface TechRoofRepository extends JpaRepository<TechRoof, Long> {
}
