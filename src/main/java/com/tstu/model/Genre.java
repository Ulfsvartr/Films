package com.tstu.model;

public enum Genre {
    CRIME("Криминал"),
    DRAMA("Драма"),
    THRILLER("Триллер"),
    ACTION("Экшен"),
    ADVENTURE("Приключение"),
    COMEDY("Комедия"),
    WAR("Боевик"),
    DOCUMENTARY("Документальный");

    private String value;

    public String getValue() {
        return value;
    }

    Genre(String value) {
        this.value = value;
    }
}
