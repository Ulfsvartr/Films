package com.tstu.ui.states;

import com.tstu.model.Role;
import com.tstu.model.UserConstants;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.ArrayList;
import java.util.List;

public class MainState implements MenuState {

    private Menu menu;
    private List<Commands> commands = new ArrayList<>();

    public MainState(Menu menu) {
        this.menu = menu;
    }

    @Override
    public List<Commands> getCommands() {
        return commands;
    }

    @Override
    public void showPreview() {
        System.out.println("Текущий пользователь - " + menu.getCurrentUser().getUsername());
    }

    @Override
    public void formCommands() {
        if (menu.getCurrentUser().getRole() == Role.GUEST) {
            this.addCommand(Commands.LOGIN_STATE);
        } else {
            this.addCommand(Commands.LOGOUT);
        }
        this.addCommand(Commands.FIND_FILM_STATE);
    }

    @Override
    public void handleCommand(Commands command) {
        switch (command) {
            case LOGIN_STATE:
                menu.setMenuState(new LoginState(menu));
                break;
            case LOGOUT:
                menu.setCurrentUser(UserConstants.guest);
                break;
            case FIND_FILM_STATE:
                menu.setMenuState(new FindFilmState(menu));
                break;
        }

        menu.display();
    }
}
