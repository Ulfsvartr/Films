package com.tstu.repositoryJDBC;

import com.tstu.csv.FilmCSV;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.FilmType;
import com.tstu.model.Genre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertToFilmJDBC {
    public static Film convertToFilm(FilmJDBC filmJDBC) throws MovieLibraryException {
        Film film = new Film();
        film.setName(filmJDBC.getName());
        film.setImdbId(filmJDBC.getImdbId());
        film.setType(FilmType.getByValue(filmJDBC.getType()));
        List<String> genresStr = Arrays.asList(filmJDBC.getGenre().split(","));
        List<Genre> genres = new ArrayList<>();
        for (String genre : genresStr) {
            genres.add(Genre.getByValue(genre));
        }
        film.setGenre(genres);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate releaseDate = LocalDate.parse(filmJDBC.getReleaseDate(),dateFormatter);
        film.setReleaseDate(releaseDate);

        return film;
    }
}
