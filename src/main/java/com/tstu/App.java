package com.tstu;


import com.tstu.*;
import com.tstu.model.Film;
import com.tstu.model.Role;
import com.tstu.model.User;
import com.tstu.model.UserConstants;
import com.tstu.repository.FilmRepository;
import com.tstu.repository.FilmRepositoryImpl;
import com.tstu.repository.UserRepository;
import com.tstu.repository.UserRepositoryImpl;
import com.tstu.service.FilmService;
import com.tstu.service.FilmServiceImpl;
import com.tstu.service.UserService;
import com.tstu.service.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static FilmRepository filmRepository = FilmRepositoryImpl.getInstance();
    private static UserRepository userRepository = UserRepositoryImpl.getInstance();
    private static FilmService filmService = new FilmServiceImpl();
    private static UserService userService = new UserServiceImpl();


    static List<Film> films;
    static User currentUser = UserConstants.guest;
    static Film currentFilm;



    public static void main( String[] args ) {
        Scanner in = new Scanner(System.in);
        String command;
        for (;;){
            System.out.print("Введите команду:");
            command=in.nextLine();
            userCommand(command);
        }
    }

    public static void userCommand(String command){
        switch (command){
            case "логин":
                Login();
                break;
            case "логаут":
                currentUser = userService.logout();
                break;
            case "найти фильм":
                findFilm();
                break;
            default:
                System.out.println("Введите корректную команду");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private static void findFilm(){
        Scanner in=new Scanner(System.in);
        System.out.print("Найти фильм по:\n" + "1)IMDB id фильма\n"+"2)Имени фильма\n"+"Выберите вариант:");
        String by=in.next();
        switch (by){
            case "1":
                findFilmByImdb();
                break;
            case "2":
                findFilmByName();
                break;
            default:
                break;

        }
    }
    private static void Login(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите username:");
        String username = in.next();
        System.out.print("Введите пароль:");
        String password = in.next();
        try {
            currentUser = userService.login(username,password);
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private static void findFilmByImdb() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название фильма:");
        String filmImdb = in.next();
        try {
            currentFilm=filmService.findById(filmImdb);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static void findFilmByName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите название фильма:");
        String filmName = in.nextLine();
        try {
            films = filmService.findByName(filmName);

            int id=1;
            for (Film film:films){
                System.out.println((id++)+") "+film.getName());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
