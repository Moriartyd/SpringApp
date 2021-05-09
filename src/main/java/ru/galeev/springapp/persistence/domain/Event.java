package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

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
    @Column(name = "time")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "relation_events_event_managers",
            joinColumns = { @JoinColumn(name = "event") },
            inverseJoinColumns = { @JoinColumn(name = "manager") })
    private List<User> eventManager = new ArrayList<User>(); // Организатор мероприятия

    @ManyToMany(mappedBy = "artistRegisteredEvents")
    private List<User> artistList = new ArrayList<User>();; // Приглашенные звезды

    @ManyToMany(mappedBy = "userRegisteredEvents")
    private List<User> userList = new ArrayList<User>();;

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

    @Getter
    @Setter
    @Column(name = "active")
    private int active;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        if (this.getId() == ((Event)obj).getId()) {
            return true;
        }
        if (this.hashCode() == ((Event)obj).hashCode()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return String.valueOf(this.id).hashCode() + this.name.hashCode();
    }
}
