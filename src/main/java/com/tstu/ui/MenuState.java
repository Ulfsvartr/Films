package com.tstu.ui;

import java.util.List;
import java.util.Scanner;

public interface MenuState {

    void showPreview();
    List<Commands> getCommands();
    void formCommands();
    void handleCommand(Commands command);

    default void enterCommand() {
        System.out.print("Введите команду:");
        Scanner in = new Scanner(System.in);
        int userChoice = Integer.parseInt(in.nextLine());
        if (userChoice <= getCommands().size() && userChoice > 0) {
            handleCommand(getCommands().get(userChoice - 1));
        } else {
            System.out.println("Неверный ввод");
        }
    }

    default void addCommand(Commands command) {
        this.getCommands().add(command);
        System.out.println(this.getCommands().size() + " - " + command.getValue());
    }

    default void display() {
        this.getCommands().clear();
        this.showPreview();
        this.formCommands();
        this.enterCommand();
    }
}
