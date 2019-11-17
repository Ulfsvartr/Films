package com.tstu.repository;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    Optional<Film> findById(String imdbId) throws MovieLibraryException;

    List<Film> findFilmList(String name, String imdbId, String type, String genre, String releaseDate) throws MovieLibraryException;

    Review saveReview(Film film, User user, Review review);
    //Film findByDate(LocalDate date) throws Exception;
}
