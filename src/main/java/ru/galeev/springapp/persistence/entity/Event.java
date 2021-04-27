package ru.galeev.springapp.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
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

    private Timestamp time; // Время проведения
    private double xCoord; // X координата геопозиции
    private double yCoord; // Y координата геопозиции
    private String place; // Место проведения
    private int cost; // Стоимость входа
    private int music; // Жанр музыки
    private String author; // Автор
    private List<EventManager> eventManager; // Организатор мероприятия
    private List<Artist> artistList; // Приглашенные звезды
    private double rating; // Рейтинг
    private int middlePrice; // Средний чек
    private int registeredVisitors; // Количество зарегистрированных пользователей
    private int attendVisitors; // Количество посетивших пользователей
}
