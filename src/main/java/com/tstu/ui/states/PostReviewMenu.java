package com.tstu.ui.states;

import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.service.FilmService;
import com.tstu.service.FilmServiceImpl;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostReviewMenu implements MenuState {
    private Menu menu;
    private Film film;
    private List<Commands> commands = new ArrayList<>();
    FilmService filmService = FilmServiceImpl.getInstance();

    public PostReviewMenu(Menu menu, Film film) {
        this.menu = menu;
        this.film = film;
    }

    @Override
    public void showPreview() {
        System.out.println("Выбранный фильм - " + this.film.getName());
    }

    @Override
    public void formCommands() {
        this.addCommand(Commands.POST_REVIEW);
        this.addCommand(Commands.BACK);
    }

    @Override
    public void handleCommand(Commands command) {
        try {
            switch (command) {
                case POST_REVIEW:
                    writeReview();
                    menu.setMenuState(new DetailFilmState(menu,film));
                    break;
                case BACK:
                    menu.setMenuState(new DetailFilmState(menu,film));
                    break;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        menu.display();
    }

    private void writeReview() {
        Scanner in = new Scanner(System.in);
        System.out.print("Напишите отзыв:");
        String text = in.nextLine();
        System.out.print("Ваша оценка(от 0 до 10):");
        int rating = in.nextInt();
        Review review = new Review(menu.getCurrentUser(),text,rating);
        film.addReview(review);
        try {
            filmService.postReview(film,menu.getCurrentUser(),review);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Commands> getCommands() {
        return commands;
    }
}
