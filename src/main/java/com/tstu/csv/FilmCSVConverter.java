package com.tstu.csv;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.FilmType;
import com.tstu.model.Genre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilmCSVConverter {
    public static Film convertToFilm(FilmCSV filmCSV) throws MovieLibraryException {
        Film film = new Film();
        film.setName(filmCSV.getName());
        film.setImdbId(filmCSV.getImdbId());
        film.setType(FilmType.getByValue(filmCSV.getType()));
        List<String> genresStr = Arrays.asList(filmCSV.getGenre().split(","));
        List<Genre> genres = new ArrayList<>();
        for (String genre : genresStr) {
            genres.add(Genre.getByValue(genre));
        }
        film.setGenre(genres);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate releaseDate = LocalDate.parse(filmCSV.getReleaseDate(),dateFormatter);
        film.setReleaseDate(releaseDate);

        return film;
    }
}
