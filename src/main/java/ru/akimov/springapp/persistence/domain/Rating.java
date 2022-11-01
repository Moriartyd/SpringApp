package ru.akimov.springapp.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
@NoArgsConstructor
public class Rating {

    @Getter
    @Setter
    @EmbeddedId
    RatingPK ratingPK;

    @Getter
    @Setter
    @Column(name = "rating")
    private Integer rating;
}
