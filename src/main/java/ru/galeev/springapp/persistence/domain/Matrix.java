package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "matrix")
@NoArgsConstructor
public class Matrix {

    @Getter
    @EmbeddedId
    MatrixPK matrixPK;

    @Column(name = "score")
    private double score;

    @Column(name = "filtered_score")
    private double filteredScore;

    public double getFilteredScore() {
        return filteredScore;
    }

    public void setFilteredScore(double filteredScore) {
        this.filteredScore = filteredScore;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    public Matrix(MatrixPK pk) {
        this.matrixPK = pk;
    }

    public Matrix(User u, Event e) {
        this.matrixPK = new MatrixPK(u, e);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Double.compare(matrix.score, score) == 0 && Double.compare(matrix.filteredScore, filteredScore) == 0 && matrixPK.equals(matrix.matrixPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrixPK);
    }
}
