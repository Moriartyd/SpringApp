package ru.galeev.springapp.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatrixPK matrixPK = (MatrixPK) o;
        return user.equals(matrixPK.user) && event.equals(matrixPK.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, event);
    }
}
