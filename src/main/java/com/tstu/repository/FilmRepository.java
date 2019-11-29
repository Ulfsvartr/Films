package com.tstu.repository;

import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    Optional<Film> findById(String imdbId);

    List<Film> findFilmList(String name, String imdbId, String type, String genre, String releaseDate) throws MovieLibraryException;

    void saveReview(Film film, User user, Review review) throws MovieLibraryException;
    //Film findByDate(LocalDate date) throws Exception;
    List<Review> getFilmReviews(String imdbId);

    void updateReview(Film film, User user, Review review) throws MovieLibraryException;

    void deleteReview(int id) throws MovieLibraryException;

    List<Review> getUserReviews(User user);
}
