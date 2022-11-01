package ru.akimov.springapp.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role implements GrantedAuthority {
    USER,
    OUTSTAFF,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    public Set<String> getSetString() {
        return Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
    }
}
