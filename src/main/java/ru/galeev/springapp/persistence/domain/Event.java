package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Getter
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "time")
    private Date time; // Время проведения

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place; // Место проведения

    @Getter
    @Setter
    @Column(name = "cost")
    private int cost; // Стоимость входа

    @Getter
    @Setter
    @Column(name = "music")
    private int music; // Жанр музыки

    @ManyToMany(mappedBy = "eventList")
    private List<EventManager> eventManager; // Организатор мероприятия

    @ManyToMany(mappedBy = "artistRegisteredEvents")
    private List<User> artistList; // Приглашенные звезды

    @ManyToMany(mappedBy = "userRegisteredEvents")
    private List<User> userList;

    @Getter
    @Setter
    @Column(name = "rating")
    private double rating; // Рейтинг

    @Getter
    @Setter
    @Column(name = "middle_price")
    private int middlePrice; // Средний чек

    @Getter
    @Setter
    @Column(name = "registered_visitors")
    private int registeredVisitors; // Количество зарегистрированных пользователей

    @Getter
    @Setter
    @Column(name = "attended_visitors")
    private int attendedVisitors; // Количество посетивших пользователей

    @Getter
    @Setter
    @Column(name = "min_age")
    private int minAge; // Возрастное ограничение
}
