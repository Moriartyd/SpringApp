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
    private List<Event> eventList;

    @Getter
    @Setter
    @Column(name = "registration_date")
    private Date registrationDate; //дата регистрации

    @Getter
    @Setter
    @Column(name = )
    private int averageVisitorsCount; //среднее количество поситителей
    private int priceRangeMin;
    private int priceRangeMax; //диапазон цены за вход




    private double averageEventRating; //Не хранится в бд, рассчитывается в java
}
