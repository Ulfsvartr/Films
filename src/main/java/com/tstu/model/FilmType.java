package com.tstu.model;

import com.tstu.exceptions.MovieLibraryError;
import com.tstu.exceptions.MovieLibraryException;

import java.util.Arrays;

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

    public  static FilmType getByValue(String value) throws MovieLibraryException {
        return Arrays.stream(FilmType.values())
                .filter(genre -> genre.getValue().equals(value))
                .findFirst()
                .orElseThrow(()->new MovieLibraryException(MovieLibraryError.GENRE_NOT_FOUND));
    }
}
