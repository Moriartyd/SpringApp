package ru.galeev.springapp.persistence.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.galeev.springapp.enums.Role;

import javax.persistence.*;

import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @Column(name = "login")
    private String login;


    @Setter
    @Column(name = "password")
    private String password;


    @Setter
    @Column(name = "name")
    private String name;


    @Setter
    @Column(name = "surname")
    private String surname;


    @Setter
    @Column(name = "email")
    private String email;

//
//    @Setter
//    @ElementCollection(targetClass = EventType.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_keywords", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<EventType> keywords = new HashSet<EventType>();

//
//    @Hidden
//    @ManyToMany
//    @JoinTable(
//            name = "relation_events_users",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "event_id") }
//    )
//    private List<Event> userRegisteredEvents;

//
//    @Hidden
//    @ManyToMany
//    @JoinTable(
//            name = "relation_events_artists",
//            joinColumns = { @JoinColumn(name = "artist") },
//            inverseJoinColumns = { @JoinColumn(name = "event") }
//    )
//    private List<Event> artistRegisteredEvents;

//
//    @Hidden
//    @ManyToMany
//    @JoinTable(
//            name = "user_followers",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "follower_id") }
//    )
//    private List<User> followers;
//
//
//    @Hidden
//    @ManyToMany
//    @JoinTable(
//            name = "user_followers",
//            joinColumns = { @JoinColumn(name = "follower_id") },
//            inverseJoinColumns = { @JoinColumn(name = "user_id") }
//    )
//    private List<User> subscriptions;
//
//
//    @ManyToMany
//    @JoinTable(
//            name = "relation_events_event_managers",
//            joinColumns = { @JoinColumn(name = "manager") },
//            inverseJoinColumns = { @JoinColumn(name = "event") }
//    )
//    private List<Event> eventList = new ArrayList<Event>();


    @Setter
    @Column(name = "age")
    private int age;

    @Setter
    @Column(name = "active")
    private boolean active;

    public boolean isActive() {
        return active;
    }


    @Setter
    @Column(name = "role")
    private String role;

//
//    @Setter
//    @Hidden
//    @OneToMany(mappedBy = "owner")
//    private List<Place> placeList = new ArrayList<Place>();
//
//
//    @Setter
//    @Column(name = "activation_code")
//    private String activationCode;

    public boolean isAdmin() {
        return role.equals(Role.ADMIN.getAuthority());
    }

    public boolean isOutstaff() {
        return role.equals(Role.OUTSTAFF.getAuthority());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleSet = new HashSet<Role>();
        Set<String> roleSetString = Role.ADMIN.getSetString();
        for (String r : roleSetString) {
            if (this.role.equals(r)) {
                roleSet.add(Role.valueOf(r));
            }
        }
        return roleSet;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static final Comparator<User> COMPARE_BY_ID = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return (int) (o1.getId() - o2.getId());
        }
    };

    public static final Comparator<User> COMPARE_BY_LOGIN = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getLogin().compareTo(o2.getLogin());
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).getId() == this.id;
        }
        return false;
    }

}
