package com.tstu.ui.menuSt;

import com.tstu.service.UserService;
import com.tstu.service.UserServiceImpl;
import com.tstu.ui.CurrentState;
import com.tstu.ui.Menu;
import com.tstu.ui.MenuState;

import java.util.Scanner;

public class LoginMenu implements MenuState {

    private Menu menu;
    private UserService userService = UserServiceImpl.getInstance();
    private CurrentState currentState;

    public LoginMenu(CurrentState currentState) {
        this.currentState = currentState;
    }

    public void changeMenu(Menu menu){
        this.menu = menu;
    }

    public void display(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите username:");
        String username = in.next();
        System.out.print("Введите пароль:");
        String password = in.next();
        try {
            currentState.setCurrentUser(userService.login(username, password));
        } catch (Exception e) {
            e.getMessage();
        } finally {
            //menu.changeState(new MainMenu(currentState));
        }

    }
}
