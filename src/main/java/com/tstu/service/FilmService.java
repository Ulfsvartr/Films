package com.tstu.service;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;

import java.util.List;

public interface FilmService {
    Film findById(String imdbId) throws Exception;

    List<Film> findFilmList(String name, String imdbId, String type, String genre, String releaseDate) throws MovieLibraryException;

    // Film findByDate(LocalDate date) throws Exception;

    Review postReview(Film film, User user, Review review) throws MovieLibraryException;

    void deleteReview(Film film, int id, User admin) throws MovieLibraryException;

    Review editReview(Film film, int id, String editedText, User admin);

    Film create(Film film);
}


