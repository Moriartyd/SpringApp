package ru.akimov.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akimov.springapp.persistence.domain.Rating;
import ru.akimov.springapp.persistence.domain.RatingPK;

public interface RatingRepository extends JpaRepository<Rating, RatingPK> {
    Rating findByRatingPK(RatingPK ratingPK);
}
