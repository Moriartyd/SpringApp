package ru.akimov.springapp.persistence.repository.tech;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akimov.springapp.persistence.domain.tech.TechExterior;

public interface TechExteriorRepository extends JpaRepository<TechExterior, Long> {
}
