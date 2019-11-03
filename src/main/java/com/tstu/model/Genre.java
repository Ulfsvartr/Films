package com.tstu.model;

import com.tstu.exceptions.MovieLibraryError;
import com.tstu.exceptions.MovieLibraryException;

import java.util.Arrays;

public enum Genre {
    CRIME("Криминал"),
    DRAMA("Драма"),
    THRILLER("Триллер"),
    ACTION("Экшн"),
    ADVENTURE("Приключенческий"),
    COMEDY("Комедия"),
    WAR("Боевик"),
    DOCUMENTARY("Документальный"),
    ANIMATION("Анимация");

    private String value;

    public String getValue() {
        return value;
    }

    Genre(String value) {
        this.value = value;
    }

    public  static Genre getByValue(String value) throws MovieLibraryException {
        return Arrays.stream(Genre.values())
                .filter(genre -> genre.getValue().equals(value))
                .findFirst()
                .orElseThrow(()->new MovieLibraryException(MovieLibraryError.GENRE_NOT_FOUND));
    }
}
