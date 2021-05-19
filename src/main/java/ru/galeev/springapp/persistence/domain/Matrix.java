package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "matrix")
@NoArgsConstructor
public class Matrix {

    @Getter
    @EmbeddedId
    MatrixPK matrixPK;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    private double score;

    public Matrix(MatrixPK pk) {
        this.matrixPK = pk;
    }

    public Matrix(User u, Event e) {
        this.matrixPK = new MatrixPK(u, e);
    }
}