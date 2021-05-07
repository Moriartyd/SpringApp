package ru.galeev.springapp.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.*;

@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {
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
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST })
    @JoinTable(
            name = "relation_events_users",
            joinColumns = { @JoinColumn(name = "event") },
            inverseJoinColumns = { @JoinColumn(name = "user") }
    )
    private List<Event> userRegisteredEvents;

    @Getter
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST })
    @JoinTable(
            name = "relation_events_artists",
            joinColumns = { @JoinColumn(name = "event") },
            inverseJoinColumns = { @JoinColumn(name = "artist") }
    )
    private List<Event> artistRegisteredEvents;

    @Getter
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST })
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

    @Setter
    @Column(name = "active")
    private boolean active;

    public boolean isActive() {
        return active;
    }

    @Getter
    @Setter
    @Column(name = "role")
    private String role;

    @Getter
    @Setter
    @Column(name = "activation_code")
    private String activationCode;

    public boolean isAdmin() {
        return role.equals(Role.ADMIN.getAuthority());
    }

    public boolean isManager() {
        return role.equals(Role.MANAGER.getAuthority());
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
        return isActive();
    }

    public static final Comparator<User> COMPARE_BY_ID = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return (int) (o1.getId() - o2.getId());
        }
    };

}
