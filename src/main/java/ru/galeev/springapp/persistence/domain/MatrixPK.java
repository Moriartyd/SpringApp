package ru.galeev.springapp.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class MatrixPK implements Serializable {

    @ManyToOne
    @JoinColumn(name="user_id")
    protected User user;

    @ManyToOne
    @JoinColumn(name="event_id")
    protected Event event;

    public MatrixPK() {}

    public MatrixPK(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
