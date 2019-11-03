package com.tstu;


import com.tstu.model.Film;
import com.tstu.repository.FilmRepository;
import com.tstu.repository.FilmRepositoryImpl;
import com.tstu.repositoryJDBC.DataBaseConnection;
import com.tstu.repositoryJDBC.TestBD;
import com.tstu.ui.CurrentState;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //CurrentState app = new CurrentState();
        //app.start();

        //TestBD.testBD();
        //Film film=null;
        //FilmRepository filmRepository = FilmRepositoryImpl.getInstance();
        //try {
        //    film = filmRepository.findById("tt0110912");
        //} catch (Exception e) {
         //   e.printStackTrace();
        //}
        //System.out.println(film.getReleaseDate().toString());
        DataBaseConnection.dB();
        //Menu menu = new Menu();
        //menu.setState(MenuStates.getMenuState(StatesName.MENU1, menu));
        //menu.render();
    }
}
