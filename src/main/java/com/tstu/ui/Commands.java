package com.tstu.ui;

public enum Commands {
    LOGIN_STATE("Авторизация"),
    LOGOUT("Выйти из аккаунта"),
    FIND_FILM_STATE("Найти фильм"),
    FIND_FILM_BY_IMDB("Найти фильм по imdb"),
    FIND_FILM_BY_NAME("Найти фильм по имени"),
    SHOW_FILM_DETAILS("Показать детали фильма"),
    SHOW_FILM_REVIEWS("Показать отзывы"),
    POST_REVIEW_STATE("Написать отзыв"),
    POST_REVIEW("Написать отзыв"),
    EDIT_REVIEW_STATE("Изменить отзыв"),
    CHOOSE_FILM("Выбрать фильм"),
    BACK("Перейти назад"),
    DELETE_REVIEW_STATE("Удалиь отзыв"),
    LOGIN("Войти в аккаунт");

    private String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
