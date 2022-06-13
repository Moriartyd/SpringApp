package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.Rating;
import ru.galeev.springapp.persistence.domain.RatingPK;

public interface RatingRepository extends JpaRepository<Rating, RatingPK> {
    Rating findByRatingPK(RatingPK ratingPK);
}
