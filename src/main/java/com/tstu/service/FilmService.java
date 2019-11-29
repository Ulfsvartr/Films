package com.tstu.service;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.filters.FilmFilter;
import com.tstu.model.Film;
import com.tstu.model.Genre;
import com.tstu.model.Review;
import com.tstu.model.User;

import java.util.List;

public interface FilmService {
    Film findById(String imdbId) throws MovieLibraryException;

    List<Film> findFilmList(String name, String imdbId, String type, String genre, String releaseDate) throws MovieLibraryException;

    List<Film> findAll() throws MovieLibraryException;

    List<Film> findByFilter(FilmFilter filmFilter) throws MovieLibraryException;

    void postReview(Film film, User user, Review review) throws MovieLibraryException;

    void deleteReview(long id, User user) throws MovieLibraryException;

    void editReview(Film film,User user , Review review) throws MovieLibraryException;

    Film create(Film film);

    List<Review> getUserReviews(User user);

    List<Review> getAllReviews();

    List<Genre> getAllGenre();


}


