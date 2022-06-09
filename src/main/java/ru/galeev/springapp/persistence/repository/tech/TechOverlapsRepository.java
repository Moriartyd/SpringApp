package ru.galeev.springapp.persistence.repository.tech;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.tech.TechOverlaps;

public interface TechOverlapsRepository extends JpaRepository<TechOverlaps, Long> {

}
