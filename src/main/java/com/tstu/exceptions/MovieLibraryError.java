package com.tstu.exceptions;

public enum MovieLibraryError {
    GENRE_NOT_FOUND("Жанр не найден!"),
    TYPE_NOT_FOUND("Тип фильма не найден"),
    FORBIDDEN_FOR_CURRENT_USER("Недоступно для данного типа пользователя."),
    SQL_QUERY_EXECUTE_ERROR("Ошибка выполнения SQL запроса"),
    NOT_CORRECT_REVIEW_ID("Неверный id для удаления отзыва."),
    FILM_NOT_FOUND("Фильм не найден"),
    PASSWORD_NOT_MATCH("Пароль не совпадает."),
    USERNAME_IS_BUSY("Пользователь с данным именем уже существует."),
    USER_NOT_FOUND("Пользователь не найден");

    private String value;

    public String getValue() {
        return value;
    }

    MovieLibraryError(String value) {
        this.value = value;
    }
}
