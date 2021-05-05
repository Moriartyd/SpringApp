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
    @GeneratedValue
    private long id;

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
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> eventList;

    @Getter
    @Setter
    @Column(name = "middle_price")
    private int middlePrice;

    @Getter
    @Setter
    @Column(name = "rating")
    private double rating;
}
