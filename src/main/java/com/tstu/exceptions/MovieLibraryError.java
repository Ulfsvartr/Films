package com.tstu.exceptions;

public enum MovieLibraryError {
    GENRE_NOT_FOUND("Жанр не найден!");

    private String value;

    public String getValue() {
        return value;
    }

    MovieLibraryError(String value) {
        this.value = value;
    }
}
