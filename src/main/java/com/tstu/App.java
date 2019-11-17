package com.tstu;


import com.tstu.repository.FilmRepository;
import com.tstu.repository.jdbc.FilmRepositoryImplJDBC;
import com.tstu.ui.Menu;
import com.tstu.utils.DataBaseMigration;


public class App 
{
    public static void main( String[] args ) {
        //DataBaseMigration.createTables();
        //DataBaseMigration.fillTables();
        Menu menu =new Menu();
        menu.display();

    }
}
