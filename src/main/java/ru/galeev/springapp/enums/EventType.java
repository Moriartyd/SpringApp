package ru.galeev.springapp.enums;

public enum EventType {
    FAMILY("Семейное"),
    CONCERT("Концерт"),
    FILM("Кино"),
    COUPLES("Для двоих"),
    FOOD("Еда"),
    DRINKS("Напитки"),
    DANCE("Танцы"),
    SMOKING("В костюмах");

    private final String description;

    EventType(String description) {
        this.description = description;
    }

    public String toString() {
        return description;
    }
}
