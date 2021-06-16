package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Propagation;
import ru.galeev.springapp.enums.EventType;
import ru.galeev.springapp.utils.Hidden;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

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
    @Hidden
    @JoinColumn(name = "place_id")
    private Place place; // Место проведения

    @Getter
    @Setter
    @Column(name = "cost")
    private int cost; // Стоимость входа

    @Getter
    @Setter
    @ElementCollection(targetClass = EventType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "event_types", joinColumns = @JoinColumn(name = "event_id"))
    @Enumerated(EnumType.STRING)
    private Set<EventType> keywords = new HashSet<EventType>();

    @Getter
    @Setter
    @ManyToMany
    @Hidden
    @JoinTable(
            name = "relation_events_event_managers",
            joinColumns = { @JoinColumn(name = "event") },
            inverseJoinColumns = { @JoinColumn(name = "manager") })
    private List<User> eventManager = new ArrayList<User>(); // Организатор мероприятия

//    @ManyToMany
//    @Hidden
//    @JoinTable(
//            name = "relation_events_artists",
//            joinColumns = { @JoinColumn(name = "event") },
//            inverseJoinColumns = { @JoinColumn(name = "artist") })
//    private List<User> artistList = new ArrayList<User>();; // Приглашенные звезды

    @Getter
    @ManyToMany
    @Hidden
    @JoinTable(
            name = "relation_events_users",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> userList = new ArrayList<User>();;

    @Getter
    @Setter
    @Column(name = "rating")
    private int rating; // Рейтинг

    @Getter
    @Setter
    @Column(name = "evaluators")
    private int evaluators; //счетчик оценивавших

    @Getter
    @Setter
    @Column(name = "registered_visitors")
    private int registeredVisitors; // Количество зарегистрированных пользователей

    @Getter
    @Setter
    @Column(name = "min_age")
    private int minAge; // Возрастное ограничение

    @Getter
    @Setter
    @Column(name = "active")
    private boolean active;

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

    public static final Comparator<Event> COMPARE_BY_ID = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return (int) (o1.getId() - o2.getId());
        }
    };

    public static final Comparator<Event> COMPARE_BY_NAME = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    @Override
    public int hashCode() {
        return String.valueOf(this.id).hashCode() + this.name.hashCode();
    }
}
