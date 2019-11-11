package com.tstu.ui.states;

import com.tstu.model.Film;
import com.tstu.service.FilmService;
import com.tstu.service.FilmServiceImpl;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.*;

public class FindFilmState implements MenuState {

    private Menu menu;
    private List<Commands> commands = new ArrayList<>();
    private FilmService filmService = FilmServiceImpl.getInstance();

    public FindFilmState(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void showPreview() {
        System.out.println("Найти фильм по: ");
    }

    @Override
    public void formCommands() {
        this.addCommand(Commands.FIND_FILM_BY_IMDB);
        this.addCommand(Commands.FIND_FILM_BY_NAME);
        this.addCommand(Commands.BACK);
    }

    @Override
    public void handleCommand(Commands command) {
        try {
            switch (command) {
                case FIND_FILM_BY_IMDB:
                    menu.setMenuState(new DetailFilmState(menu, findFilmByImdb()));
                    break;
                case FIND_FILM_BY_NAME:
                    menu.setMenuState(new ChooseFilmState(menu, findFilmByName()));
                    break;
                case BACK:
                    menu.setMenuState(new MainState(menu));
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        menu.display();
    }

    @Override
    public List<Commands> getCommands() {
        return this.commands;
    }


    private Film findFilmByImdb() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите Imdb фильма:");
        String filmImdb = in.next();
        return filmService.findFilmList("",filmImdb,"","","").get(0);
    }

    private List<Film> findFilmByName() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название фильма:");
        String filmName = in.nextLine();
        List<Film> films = filmService.findFilmList(filmName,"","","","");
        films.sort((Film o1, Film o2) -> {
            if (o1.getRating() - o2.getRating() < 0) {
                return -1;
            }
            if (o1.getRating() - o2.getRating() > 0) {
                return 1;
            }
            return 0;
        });
        return films;
    }


}
