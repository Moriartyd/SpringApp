package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.galeev.springapp.persistence.domain.user.Contractor;
import ru.galeev.springapp.persistence.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "contract_request")
@NoArgsConstructor
public class ContractRequest {

    @Id
    @Getter
    @JoinColumn(referencedColumnName = "calculation_id")
    private Long calculationId;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    @ManyToOne
    @Column(name = "contractor_id")
    private Contractor contractor;

    @Column(name = "status_contractor")
    private Integer statusContractor;
    @Column(name = "status_user")
    private Integer statusUser;
}
