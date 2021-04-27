package ru.galeev.springapp.persistence.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @Getter
    @GeneratedValue
    private long id;
    private String name;
    private String music;
    private String instagram;
}
