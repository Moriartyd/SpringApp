package ru.akimov.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.akimov.springapp.persistence.domain.Calculation;
import ru.akimov.springapp.persistence.domain.ContractRequest;
import ru.akimov.springapp.persistence.domain.user.Contractor;
import ru.akimov.springapp.persistence.domain.user.User;

import java.util.List;

public interface ContractRequestRepository extends JpaRepository<ContractRequest, Long> {

    ContractRequest findByCalculationId(Long calculationId);

//    @Query(value = "select m from ContractRequest m where m.user = ?1")
    List<ContractRequest> findByUser(User user);

    @Query(value = "select m from ContractRequest m where m.contractor = ?1 and m.statusContractor = ?2")
    List<ContractRequest> findByContractorAccepted(Contractor contractor, Integer accept);

    List<ContractRequest> findContractRequestsByContractor(Contractor contractor);

    List<ContractRequest> findContractRequestsByCalculation(Calculation calculation);
}
