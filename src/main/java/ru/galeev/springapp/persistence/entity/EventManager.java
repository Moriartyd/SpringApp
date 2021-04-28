package ru.galeev.springapp.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event_manager")
public class EventManager {
    @Id
    @Getter
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    @Column(name = "registration_date")
    private Date registrationDate; //дата регистрации

    @Getter
    @Setter
    @Column(name = "average_visitors")
    private int averageVisitorsCount; //среднее количество поситителей

    @Getter
    @Setter
    @Column(name = "min_price")
    private int priceRangeMin;

    @Getter
    @Setter
    @Column(name = "max_price")
    private int priceRangeMax; //диапазон цены за вход

    @Getter
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "relation_events_event_managers",
            joinColumns = { @JoinColumn(name = "event") },
            inverseJoinColumns = { @JoinColumn(name = "manager") }
    )
    private List<Event> eventList;//DONE

//    private double averageEventRating; //
}
