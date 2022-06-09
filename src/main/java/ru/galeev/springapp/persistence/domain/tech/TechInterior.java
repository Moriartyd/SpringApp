package ru.galeev.springapp.persistence.domain.tech;

import lombok.Getter;
import lombok.Setter;
import ru.galeev.springapp.persistence.domain.AbstractTechTable;

import javax.persistence.*;

@Entity
@Table(name = "tech_interior")
public class TechInterior {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "word")
    private String word;
}