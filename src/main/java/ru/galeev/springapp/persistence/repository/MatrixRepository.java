package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.galeev.springapp.persistence.domain.Event;
import ru.galeev.springapp.persistence.domain.Matrix;
import ru.galeev.springapp.persistence.domain.MatrixPK;
import ru.galeev.springapp.persistence.domain.User;

import java.util.List;

public interface MatrixRepository extends JpaRepository<Matrix, MatrixPK> {
    Matrix findByMatrixPK(MatrixPK matrixPK);
    @Query(value = "select m from Matrix m where m.matrixPK.user.id = ?1")
    List<Matrix> findByUser(User user);
    @Query(value = "select m.matrixPK.user from Matrix m " +
            "where exists " +
            "(select a.matrixPK.user from Matrix a where a.matrixPK.event = ?1 and a.score > 0) " +
            "and exists " +
            "(select b.matrixPK.user from Matrix b where b.matrixPK.event = ?2 and b.score > 0)")
    List<User> getU(Event x, Event y);

    @Query(value = "select m.matrixPK.event from Matrix m where m.matrixPK.user = ?1 and m.score = 0")
    List<Event> getUnscoredByUser(User u);
}
