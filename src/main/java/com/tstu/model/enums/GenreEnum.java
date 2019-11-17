package com.tstu.model.enums;

import com.tstu.exceptions.MovieLibraryError;
import com.tstu.exceptions.MovieLibraryException;

import java.util.Arrays;

public enum GenreEnum {
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

    GenreEnum(String value) {
        this.value = value;
    }

    public  static GenreEnum getByValue(String value) throws MovieLibraryException {
        return Arrays.stream(GenreEnum.values())
                .filter(genre -> genre.getValue().equals(value))
                .findFirst()
                .orElseThrow(()->new MovieLibraryException(MovieLibraryError.GENRE_NOT_FOUND));
    }
}
