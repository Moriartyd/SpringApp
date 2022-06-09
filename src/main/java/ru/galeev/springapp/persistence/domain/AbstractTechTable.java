package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AbstractTechTable {

//    @Id
//    @Getter
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//
//    @Getter
//    @Setter
//    @Column(name = "word")
    private String word;
}
