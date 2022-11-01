package ru.akimov.springapp.persistence.repository.tech;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akimov.springapp.persistence.domain.tech.TechCarrierWall;

public interface TechCarrierWallRepository extends JpaRepository<TechCarrierWall, Long> {
//    TechCarrierWall findById
}
