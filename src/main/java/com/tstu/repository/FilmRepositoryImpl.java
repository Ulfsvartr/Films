package com.tstu.repository;

import com.tstu.model.Film;
import com.tstu.model.FilmType;
import com.tstu.model.Genre;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilmRepositoryImpl implements FilmRepository {

    private static List<Film> films = new ArrayList<>();

    static {
        films.add(new Film("tt0093058", FilmType.FILM,"Цельнометаллическая оболочка",new ArrayList<>(Arrays.asList(Genre.DRAMA, Genre.WAR)), LocalDate.of(1987, Month.JULY, 10)));
        films.add(new Film("tt0110912", FilmType.FILM,"Криминальное чтиво",new ArrayList<>(Arrays.asList(Genre.CRIME, Genre.DRAMA)), LocalDate.of(1994, Month.SEPTEMBER, 23)));
        films.add(new Film("tt0105236", FilmType.FILM,"Бешеные псы",new ArrayList<>(Arrays.asList(Genre.CRIME, Genre.DRAMA,Genre.THRILLER)), LocalDate.of(1992, Month.JANUARY, 21)));
        films.add(new Film("tt0944947", FilmType.TVSERIAS,"Игра престолов",new ArrayList<>(Arrays.asList(Genre.ACTION, Genre.ADVENTURE,Genre.DRAMA)), LocalDate.of(2011, Month.APRIL, 17)));
        films.add(new Film("tt2388725", FilmType.SHORT,"Бумажный роман",new ArrayList<>(Arrays.asList(Genre.COMEDY)), LocalDate.of(2012, Month.NOVEMBER, 2)));
    }

    private static FilmRepository instance;

    private FilmRepositoryImpl() {
    }

    public static FilmRepository getInstance() {
        if (instance == null) {
            instance = new FilmRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Film findById(String imdbId) throws Exception {
        return films.stream()
                .filter(film -> film.getImdbId().equals(imdbId))
                .findFirst()
                .orElseThrow(()-> new Exception("Фильм не найден!"));
    }

    @Override
    public List<Film> findByName(String name) throws Exception {
        return films.stream()
                .filter(film -> film.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public Film findByDate(LocalDate date) throws Exception {
        return films.stream()
                .filter(film -> film.getReleaseDate().equals(date))
                .findAny()
                .orElseThrow(()-> new Exception("Фильм не найден!"));
    }
}
