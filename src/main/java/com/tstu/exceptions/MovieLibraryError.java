package com.tstu.exceptions;

public enum MovieLibraryError {
    GENRE_NOT_FOUND("Жанр не найден!"),
    FORBIDDEN_FOR_CURRENT_USER("Недоступно для данного типа пользователя."),
    SQL_QUERY_EXECUTE_ERROR("Ошибка выполнения SQL запроса"),
    NOT_CORRECT_REVIEW_ID("Неверный id для удаления отзыва."),
    FILM_NOT_FOUND("Фильм не найден."),
    REVIEW_NOT_FOUND("Данного отзыва не существует."),
    PASSWORD_NOT_MATCH("Пароль не совпадает."),
    USERNAME_IS_BUSY("Пользователь с данным именем уже существует."),
    USER_NOT_FOUND("Пользователь не найден"),
    USERNAME_OR_PASSWORD_EMPTY("Логин или пароль пустые");

    private String value;

    public String getValue() {
        return value;
    }

    MovieLibraryError(String value) {
        this.value = value;
    }
}
