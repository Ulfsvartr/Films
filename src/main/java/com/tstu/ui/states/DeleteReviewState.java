package com.tstu.ui.states;

import com.tstu.model.Film;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.ArrayList;
import java.util.List;

public class DeleteReviewState implements MenuState {

    private Menu menu;
    private Film film;
    private List<Commands> commands = new ArrayList<>();

    public DeleteReviewState(Menu menu, Film film) {
        this.menu = menu;
        this.film = film;
    }

    @Override
    public void showPreview() {

    }

    @Override
    public void formCommands() {

    }

    @Override
    public void handleCommand(Commands command) {

    }

    @Override
    public List<Commands> getCommands() {
        return commands;
    }
}
