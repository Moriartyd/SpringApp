package ru.galeev.springapp.persistence.repository.tech;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.tech.TechCarrierWall;

import java.util.Optional;

public interface TechCarrierWallRepository extends JpaRepository<TechCarrierWall, Long> {
//    TechCarrierWall findById
}
