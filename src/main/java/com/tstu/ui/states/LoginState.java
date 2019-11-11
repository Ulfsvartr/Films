package com.tstu.ui.states;

import com.tstu.service.UserService;
import com.tstu.service.UserServiceImpl;
import com.tstu.ui.Commands;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginState implements MenuState {

    private Menu menu;
    private List<Commands> commands = new ArrayList<>();
    private UserService userService = UserServiceImpl.getInstance();

    public LoginState(Menu menu) {
        this.menu = menu;
    }


    @Override
    public void showPreview() {

    }

    @Override
    public void formCommands() {
        this.addCommand(Commands.LOGIN);
        this.addCommand(Commands.BACK);
    }

    @Override
    public void handleCommand(Commands command) {
        try {
            switch (command) {
                case LOGIN:
                    login();
                    menu.setMenuState(new MainState(menu));
                    break;
                case BACK:
                    menu.setMenuState(new MainState(menu));
                    break;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        menu.display();
    }

    @Override
    public List<Commands> getCommands() {
        return this.commands;
    }

    private void login() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите username:");
        String username = in.next();
        System.out.print("Введите пароль:");
        String password = in.next();
        menu.setCurrentUser(userService.login(username, password));
    }
}
