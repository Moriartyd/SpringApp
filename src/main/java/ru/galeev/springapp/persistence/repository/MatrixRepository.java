package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.galeev.springapp.persistence.domain.Matrix;
import ru.galeev.springapp.persistence.domain.MatrixPK;
import ru.galeev.springapp.persistence.domain.User;

import java.util.List;

public interface MatrixRepository extends JpaRepository<Matrix, MatrixPK> {
    List<Matrix> findByMatrixPK(MatrixPK matrixPK);
    @Query(value = "select m from Matrix m where m.matrixPK.user.id = ?1")
    List<Matrix> findByUser(User user);
}
