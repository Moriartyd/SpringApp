package ru.akimov.springapp.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.akimov.springapp.persistence.domain.user.Contractor;
import ru.akimov.springapp.persistence.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "contract_request")
@NoArgsConstructor
@Setter
@Getter
public class ContractRequest {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calculation_id")
    private Calculation calculation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @Column(name = "status_contractor")
    private Integer statusContractor;
    @Column(name = "status_user")
    private Integer statusUser;
}
