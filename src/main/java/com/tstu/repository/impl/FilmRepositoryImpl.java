package com.tstu.repository.impl;

import com.tstu.csv.CSVParser;
import com.tstu.csv.FilmCSV;
import com.tstu.repository.FilmRepository;
import com.tstu.utils.FilmHelper;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilmRepositoryImpl implements FilmRepository {

    private static List<Film> films = new ArrayList<>();

    static {
        try {
            List<FilmCSV> filmsFromFile = CSVParser.getFilmsFromFile("/files/FilmsCSV.csv");
            for (FilmCSV s : filmsFromFile) {
                films.add(FilmHelper.convertToFilm(s));
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
    public Optional<Film> findById(String imdbId) {
        return films.stream()
                .filter(film -> film.getImdbId().equals(imdbId))
                .findFirst();
    }

    @Override
    public List<Film> findFilmList(String name,String imdbId,String type, String genre,String releaseDate){
        return films.stream()
                .filter(film -> film.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public Review saveReview(Film film, User user, Review review) {
        film.addReview(review);
        return review;
    }


    //@Override
    //public Film findByDate(LocalDate date) throws Exception {
    //   return films.stream()
    //            .filter(film -> film.getReleaseDate().equals(date))
    //            .findAny()
    //            .orElseThrow(()-> new Exception("Фильм не найден!"));
    //}
}
