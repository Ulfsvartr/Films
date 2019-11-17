package com.tstu.utils;

import com.tstu.csv.FilmCSV;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.Genre;
import com.tstu.model.enums.FilmType;
import com.tstu.repository.GenreRepository;
import com.tstu.repository.jdbc.GenreRepositoryImplJDBC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmHelper {
    private final static DateTimeFormatter csvDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final static GenreRepository genreRepository = GenreRepositoryImplJDBC.getInstance();

    public static Film convertToFilm(FilmCSV filmCSV) throws MovieLibraryException {
        return buildFilm(
                filmCSV.getImdbId(),
                filmCSV.getType(),
                filmCSV.getName(),
                filmCSV.getGenre(),
                filmCSV.getReleaseDate(),
                csvDateFormatter);
    }

    public static Film buildFilm(String imdbID, String type, String name,
                                 String genres, String date, DateTimeFormatter formatter) throws MovieLibraryException {
        Film film = new Film();
        film.setName(name);
        film.setImdbId(imdbID);
        film.setType(FilmType.getByValue(type));
        String[] genreArray = genres.split(",");
        List<Genre> genreList = new ArrayList<>();
        for (String genreStr : genreArray) {
            Optional<Genre> genre = genreRepository.findByName(genreStr);
            genre.ifPresent(genreList::add);
        }
        film.setGenres(genreList);
        LocalDate releaseDate = LocalDate.parse(date,formatter);
        film.setReleaseDate(releaseDate);
        return film;
    }
}
