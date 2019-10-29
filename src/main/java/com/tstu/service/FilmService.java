package com.tstu.service;

import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;

import java.time.LocalDate;
import java.util.List;

public interface FilmService {
    Film findById(String imdbId) throws Exception;

    List<Film> findByName(String name) throws Exception;

    Film findByDate(LocalDate date) throws Exception;

    Review postReview(Film film, User user, String text, int rating) throws Exception;

    void deleteReview(Film film, int id,User admin) throws Exception;// ???

    Review editReview(Film film, int id, String editedText, User admin);

    Film create(Film film);

    String showFilmDetails(Film film);

    String showFilmReviews(Film film);
}


