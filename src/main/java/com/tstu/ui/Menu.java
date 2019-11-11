package com.tstu.ui;

import com.tstu.model.User;
import com.tstu.model.UserConstants;
import com.tstu.ui.states.MainState;

public class Menu {

    private MenuState menuState;
    private User currentUser = UserConstants.guest;

    public Menu() {
        this.menuState = new MainState(this);
    }

    public void display() {
        menuState.display();
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
