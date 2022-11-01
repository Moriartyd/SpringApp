package ru.akimov.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akimov.springapp.persistence.domain.user.Contractor;
import ru.akimov.springapp.persistence.domain.user.User;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    Contractor findByUser(User user);

}
