package com.tstu.model;

public enum FilmType {
    FILM("Фильм"),
    SHORT("Короткометражный фильм"),
    TVSERIAS("Сериал");


    private String value;

    public String getValue() {
        return value;
    }

    FilmType(String value) {
        this.value = value;
    }
}
