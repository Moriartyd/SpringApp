package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.galeev.springapp.persistence.domain.Place;
import ru.galeev.springapp.persistence.domain.User;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByOwner(User owner);

    @Query( value = "select p from Place p where p.id=61")
    Place getServicePlace();
}
