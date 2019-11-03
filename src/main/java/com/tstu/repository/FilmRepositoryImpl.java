package com.tstu.repository;

import com.tstu.csv.CSVParser;
import com.tstu.csv.FilmCSV;
import com.tstu.csv.FilmCSVConverter;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.FilmType;
import com.tstu.model.Genre;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilmRepositoryImpl implements FilmRepository {

    private static List<Film> films = new ArrayList<>();

    static {
        try {
            List<FilmCSV> filmsFromFile = CSVParser.getFilmsFromFile("/files/FilmsCSV.csv");
            for (FilmCSV s : filmsFromFile) {
                films.add(FilmCSVConverter.convertToFilm(s));
            }
        } catch (FileNotFoundException | MovieLibraryException e) {
            e.printStackTrace();
        }
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
    public List<Film> findByName(String name){
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
