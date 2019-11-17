package com.tstu.ui.states;

import com.tstu.model.Film;
import com.tstu.model.Genre;
import com.tstu.model.Review;
import com.tstu.model.enums.Role;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailFilmState implements MenuState {
    private Menu menu;
    private Film film;
    private List<Commands> commands = new ArrayList<>();
    //private FilmService filmService = FilmServiceImpl.getInstance();

    public DetailFilmState(Menu menu, Film film) {
        this.menu = menu;
        this.film = film;
    }

    @Override
    public void showPreview() {
        System.out.println("Выбранный фильм - " + this.film.getName());
    }

    @Override
    public void formCommands() {
        this.addCommand(Commands.SHOW_FILM_DETAILS);
        this.addCommand(Commands.SHOW_FILM_REVIEWS);
        if (menu.getCurrentUser().getRole() == Role.USER) {
            this.addCommand(Commands.POST_REVIEW_STATE);
        }
        if (menu.getCurrentUser().getRole() == Role.ADMIN) {
            this.addCommand(Commands.EDIT_REVIEW_STATE);
            this.addCommand(Commands.DELETE_REVIEW_STATE);
        }
        this.addCommand(Commands.BACK);
    }

    @Override
    public void handleCommand(Commands command) {
        switch (command) {
            case SHOW_FILM_DETAILS:
                System.out.println(showFilmDetails(film));
                break;
            case SHOW_FILM_REVIEWS:
                System.out.println(showFilmReviews(film));
                break;
            case POST_REVIEW_STATE:
                menu.setMenuState(new PostReviewMenu(menu, film));
                break;
            case EDIT_REVIEW_STATE:
                break;
            case DELETE_REVIEW_STATE:
                break;
            case BACK:
                menu.setMenuState(new FindFilmState(menu));
        }
        menu.display();
    }

    private String showFilmReviews(Film film) {
        StringBuilder reviews = new StringBuilder();
        reviews.append("Название фильма: ").append(film.getName()).append("\nРейтинг: ").append(film.getRating()).append("\nОтзывы:\n");
        int id = 1;
        for (Review r : film.getReviews()) {
            reviews.append(id++).append(") ").append(r.getAuthor()).append("\n").append(r.getText()).append("\nОценка пользователя: ").append(r.getRating()).append("\n");
        }
        return reviews.toString();
    }

    private String showFilmDetails(Film film) {
        String filmDetail = "Название фильма: " + film.getName();
        filmDetail += "\nIMDB: " + film.getImdbId();
        filmDetail += "\nТип фильма: " + film.getType().getValue();
        filmDetail += "\nЖанр: " + film.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.joining(", "));
        filmDetail += "\nДата выхода: " + film.getReleaseDate();
        filmDetail += "\nРэйтинг: " + film.getRating();
        return filmDetail;
    }

    @Override
    public List<Commands> getCommands() {
        return this.commands;
    }
}
