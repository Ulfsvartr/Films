package com.tstu;


import com.tstu.ui.Menu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //CurrentState app = new CurrentState();
        //app.start();
        Menu menu =new Menu();
        menu.display();

        //TestBD.testBD();
        //List<Film> films=null;
        //FilmRepository filmRepository = FilmRepositoryImplJDBC.getInstance();
        //try {
        //    films = filmRepository.findFilmList("","","","","");
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        //for (Film film : films){
        //    System.out.println(film);
        //}
        //System.out.println(film.getReleaseDate().toString());
        //DataBaseConnection.dB();
        //Menu menu = new Menu();
        //menu.setState(MenuStates.getMenuState(StatesName.MENU1, menu));
        //menu.render();
    }
}
