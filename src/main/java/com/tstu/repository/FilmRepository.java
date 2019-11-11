package com.tstu.repository;

import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;

import java.time.LocalDate;
import java.util.List;

public interface FilmRepository {
    //Film findById(String imdbId) throws Exception;
    List<Film> findFilmList(String name,String imdbId,String type, String genre,String releaseDate) throws Exception;
    Review saveReview(Film film, User user, String text, int rating);
    //Film findByDate(LocalDate date) throws Exception;
}
