package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.Matrix;
import ru.galeev.springapp.persistence.domain.MatrixPK;

public interface MatrixRepository extends JpaRepository<Matrix, MatrixPK> {


}
