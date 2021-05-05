package ru.galeev.springapp.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @Column(name = "login")
    private String login;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "surname")
    private String surname;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "role")
    private int isAuthorArtis; // 0 - никто; 1 - Артист

    @Getter
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "relation_events_users",
            joinColumns = { @JoinColumn(name = "event") },
            inverseJoinColumns = { @JoinColumn(name = "user") }
    )
    private List<Event> userRegisteredEvents;

    @Getter
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "relation_events_event_artists",
            joinColumns = { @JoinColumn(name = "event") },
            inverseJoinColumns = { @JoinColumn(name = "artist") }
    )
    private List<Event> artistRegisteredEvents;

    @Getter
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_friendships",
            joinColumns = { @JoinColumn(name = "user_1") },
            inverseJoinColumns = { @JoinColumn(name = "user_2") }
    )
    private List<User> friends;

    @Getter
    @Setter
    @Column(name = "age")
    private int age;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.name = null;
        this.email = null;
        this.isAuthorArtis = 0;
        this.surname = null;
        this.age = 0;
    }
}
