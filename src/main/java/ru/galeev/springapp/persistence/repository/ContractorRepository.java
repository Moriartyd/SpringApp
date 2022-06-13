package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.user.Contractor;
import ru.galeev.springapp.persistence.domain.user.User;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    Contractor findByUser(User user);

}
