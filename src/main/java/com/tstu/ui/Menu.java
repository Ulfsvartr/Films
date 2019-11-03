package com.tstu.ui;

public class Menu {
    private MenuState menuState;

    public Menu(MenuState initialMenuState) {
        this.menuState = initialMenuState;
        initialMenuState.changeMenu(this);
    }

    public void changeState(MenuState menuState) {
        this.menuState = menuState;
    }

    public void display() {
        menuState.display();
    }
}
