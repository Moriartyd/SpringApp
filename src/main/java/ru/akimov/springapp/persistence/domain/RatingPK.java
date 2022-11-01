package ru.akimov.springapp.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.akimov.springapp.persistence.domain.user.Contractor;
import ru.akimov.springapp.persistence.domain.user.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RatingPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    protected Contractor contractor;
}
