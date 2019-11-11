package com.tstu.ui.states;

import com.tstu.model.Film;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChooseFilmState implements MenuState {

    private Menu menu;
    private List<Film> films;
    private List<Commands> commands = new ArrayList<>();


    public ChooseFilmState(Menu menu, List<Film> films) {
        this.menu = menu;
        this.films = films;
    }

    @Override
    public void showPreview() {
        for (Film film : films) {
            int filmIndex = films.indexOf(film) + 1;
            System.out.println(filmIndex + ") " + film.getName());
        }
    }

    @Override
    public void formCommands() {
        this.addCommand(Commands.CHOOSE_FILM);
        this.addCommand(Commands.BACK);
    }

    @Override
    public void handleCommand(Commands command) {
        try {
            switch (command) {
                case CHOOSE_FILM:
                    menu.setMenuState(new DetailFilmState(menu, chooseFilm()));
                    break;
                case BACK:
                    menu.setMenuState(new FindFilmState(menu));
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        menu.display();
    }

    private Film chooseFilm() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер фильма:");
        int filmNumber = in.nextInt();
        return films.get(filmNumber - 1);
    }

    @Override
    public List<Commands> getCommands() {
        return this.commands;
    }
}
