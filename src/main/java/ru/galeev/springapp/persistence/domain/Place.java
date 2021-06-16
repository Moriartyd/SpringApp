package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "latitude")
    private double latitude; // X координата геопозиции

    @Getter
    @Setter
    @Column(name = "longitude")
    private double longitude; // Y координата геопозиции

    @Getter
    @Setter
    @OneToMany(mappedBy = "place", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST }, fetch = FetchType.LAZY)
    
    private List<Event> eventList;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn (name="owner")
    private User owner;
}
