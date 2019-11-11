package com.tstu.ui.states;

import com.tstu.model.Film;
import com.tstu.model.Role;
import com.tstu.service.FilmService;
import com.tstu.service.FilmServiceImpl;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.ArrayList;
import java.util.List;

public class DetailFilmState implements MenuState {
    private Menu menu;
    private Film film;
    private List<Commands> commands = new ArrayList<>();
    private FilmService filmService = FilmServiceImpl.getInstance();

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
        if (menu.getCurrentUser().getRole() == Role.USER){
            this.addCommand(Commands.POST_REVIEW_STATE);
        }
        if (menu.getCurrentUser().getRole()==Role.ADMIN){
            this.addCommand(Commands.EDIT_REVIEW_STATE);
            this.addCommand(Commands.DELETE_REVIEW_STATE);
        }
        this.addCommand(Commands.BACK);
    }

    @Override
    public void handleCommand(Commands command) {
        switch (command) {
            case SHOW_FILM_DETAILS:
                System.out.println(filmService.showFilmDetails(film));
                break;
            case SHOW_FILM_REVIEWS:
                System.out.println(filmService.showFilmReviews(film));
                break;
            case POST_REVIEW_STATE:
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

    @Override
    public List<Commands> getCommands() {
        return this.commands;
    }
}
