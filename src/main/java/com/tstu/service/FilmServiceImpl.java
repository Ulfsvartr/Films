package com.tstu.service;

import com.tstu.exceptions.MovieLibraryError;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;
import com.tstu.model.enums.Role;
import com.tstu.repository.FilmRepository;
import com.tstu.repository.jdbc.FilmRepositoryImplJDBC;

import java.util.List;

public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository = FilmRepositoryImplJDBC.getInstance();

    private static FilmService instance;

    private FilmServiceImpl() {
    }

    public static FilmService getInstance() {
        if (instance == null) {
            instance = new FilmServiceImpl();
        }
        return instance;
    }

    @Override
    public Film findById(String imdbId) throws Exception {
        return filmRepository.findById(imdbId)
                .orElseThrow(() -> new MovieLibraryException(MovieLibraryError.FILM_NOT_FOUND));
    }

    @Override
    public List<Film> findFilmList(String name, String imdbId, String type, String genre, String releaseDate) throws MovieLibraryException {
        List<Film> filmList = filmRepository.findFilmList(name, imdbId, type, genre, releaseDate);
        if (!filmList.isEmpty()) {
            return filmList;
        } else {
            throw new MovieLibraryException(MovieLibraryError.FILM_NOT_FOUND);
        }
    }

    //@Override
    //public Film findByDate(LocalDate date) throws Exception {
    //    return filmRepository.findByDate(date);
    //}

    @Override
    public Review postReview(Film film, User user, Review review) throws MovieLibraryException {
        if (user.getRole() == Role.USER) {
            return filmRepository.saveReview(film, user, review);
        } else {
            throw new MovieLibraryException(MovieLibraryError.FORBIDDEN_FOR_CURRENT_USER);
        }
    }

    @Override
    public void deleteReview(Film film, int id, User admin) throws MovieLibraryException {
        if (admin.getRole() == Role.ADMIN) {
            try {
                film.getReviews().remove(id - 1);
                film.calculateAverageRating();
            } catch (Exception e) {
                throw new MovieLibraryException(MovieLibraryError.NOT_CORRECT_REVIEW_ID);
            }
        }
    }

    @Override
    public Review editReview(Film film, int id, String editedText, User admin) {
        if (admin.getRole() == Role.ADMIN) {
            film.getReviews().get(id).setText(editedText + "\n Отредактированно Админом.");
        }
        return null;
    }

    @Override
    public Film create(Film film) {
        return null;
    }

}
