package ru.galeev.springapp.persistence.repository.tech;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.tech.TechRoof;

public interface TechRoofRepository extends JpaRepository<TechRoof, Long> {
}
