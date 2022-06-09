package ru.galeev.springapp.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.galeev.springapp.persistence.domain.Calculation;
import ru.galeev.springapp.persistence.domain.ContractRequest;
import ru.galeev.springapp.persistence.domain.user.Contractor;
import ru.galeev.springapp.persistence.domain.user.User;

import java.util.List;

public interface ContractRequestRepository extends JpaRepository<ContractRequest, Long> {

    ContractRequest findByCalculationId(Long calculationId);

//    @Query(value = "select m from ContractRequest m where m.user = ?1")
    List<ContractRequest> findByUser(User user);

    @Query(value = "select m from ContractRequest m where m.contractor = ?1 and m.statusContractor = ?2")
    List<ContractRequest> findByContractorAccepted(Contractor contractor, Integer accept);
//    @Query(value = "select distinct m.matrixPK.user from Matrix m " +
//            "where exists " +
//            "(select a.matrixPK.user from Matrix a where a.matrixPK.event = ?1 and a.score > 0) " +
//            "and exists " +
//            "(select b.matrixPK.user from Matrix b where b.matrixPK.event = ?2 and b.score > 0)")

//    @Query(value = "select m.matrixPK.user from Matrix m where (m.matrixPK.event = ?1 or m.matrixPK.event = ?2) and m.score <> 0")
//    @Query(value = "from (select x.user_id   as user_id,\n" +
//            "             x.event_id  as first_event,\n" +
//            "             x.score     as first_score,\n" +
//            "             second.event_id as second_event,\n" +
//            "             second.score    as second_score\n" +
//            "      from Matrix x\n" +
//            "               full join matrix second on first.USER_ID = second.USER_ID)\n" +
//            "where first_event = ?1\n" +
//            "  and second_event = ?2\n" +
//            "  and second_score <> 0\n" +
//            "  and first_score <> 0")
//    @Query(value = "select m.matrixPK.user from ContractRequest m where m.matrixPK.event = ?1 and m.score <> 0")
//    List<User> getU(Calculation x);
//
//    @Query(value = "select m.matrixPK.event from ContractRequest m where m.matrixPK.user = ?1 and m.score = 0")
//    List<Calculation> getUnscoredByUser(User u);
}
