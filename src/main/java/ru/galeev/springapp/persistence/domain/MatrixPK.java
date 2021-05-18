package ru.galeev.springapp.persistence.domain;

import java.io.Serializable;

public class MatrixPK implements Serializable {
    protected User user;
    protected Event event;

    public MatrixPK() {}

    public MatrixPK(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
