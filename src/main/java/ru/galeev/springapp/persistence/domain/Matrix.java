package ru.galeev.springapp.persistence.domain;

import javax.persistence.*;

@Entity
@IdClass(MatrixPK.class)
@Table(name = "matrix")
public class Matrix {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    private double score;

    public Matrix(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
