package com.tstu.ui.menuSt;

import com.tstu.model.Role;
import com.tstu.ui.CurrentState;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements MenuState {
    private Menu menu;
    private List<Commands> commands = new ArrayList<>();

    private CurrentState currentState = new CurrentState();//Spring

    @Override
    public void changeMenu(Menu menu) {
        this.menu = menu;
    }

    public MainMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        int id = 1;
        System.out.println("Главное меню");
        System.out.println("Текущий пользователь - " + currentState.getCurrentUser().getUsername());

        if (currentState.getCurrentFilm() != null) {
            System.out.println("Выбранный фильм - " + currentState.getCurrentFilm().getName());
        }

        if (currentState.getCurrentUser().getRole() != Role.GUEST) {
            System.out.println((id++) + " - Log In");
            commands.add(Commands.LOGIN);
        } else {
            System.out.println((id++) + " - Log Out");
            commands.add(Commands.LOGOUT);
        }

        System.out.println((id++) + " - Найти фильм");
        commands.add(Commands.FIND_FILM);

        if (currentState.getCurrentFilm() != null) {
            System.out.println((id++) + " - Показать детали фильма");
            commands.add(Commands.SHOW_FILM_DETAILS);
            System.out.println((id++) + " - Показать отзывы фильма");
            commands.add(Commands.SHOW_FILM_REVIEWS);
        }

        if (currentState.getCurrentUser().getRole() == Role.USER && currentState.getCurrentFilm() != null) {
            System.out.println((id++) + " - Написать отзыв");
            commands.add(Commands.POST_REVIEW);
        }

        if (currentState.getCurrentUser().getRole() == Role.ADMIN && currentState.getCurrentFilm() != null) {
            System.out.println((id++) + " - Изменить отзыв пользователя");
            commands.add(Commands.EDIT_REVIEW);
            System.out.println((id++) + " - Удалить отзыв пользователя");
            commands.add(Commands.DELETE_REVIEW);
        }

    }

    private void chooseCommand(List<Commands> commands, int userChoice) {
        if (userChoice <= commands.size() && userChoice > 0) {
            commandExecution(commands.get(userChoice - 1));
        } else {
            System.out.println("Неверный ввод");
        }
    }


    private void commandExecution(Commands command) {
        switch (command) {
            case LOGIN:
                menu.changeState(new LoginMenu(currentState));
                break;
            case LOGOUT:
                break;
            case FIND_FILM:
                break;
            case SHOW_FILM_DETAILS:
                break;
            case SHOW_FILM_REVIEWS:
                break;
            case POST_REVIEW:
                break;
            case EDIT_REVIEW:
                break;
            case DELETE_REVIEW:
                break;
        }
    }
}
