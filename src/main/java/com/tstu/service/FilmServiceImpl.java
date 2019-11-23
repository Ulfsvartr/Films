package com.tstu.service;

import com.tstu.exceptions.MovieLibraryError;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.filters.FilmFilter;
import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;
import com.tstu.model.enums.Role;
import com.tstu.repository.FilmRepository;
import com.tstu.repository.jdbc.FilmRepositoryImplJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmRepository filmRepository;// = FilmRepositoryImplJDBC.getInstance();

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

    @Override
    public List<Film> findAll() throws MovieLibraryException {
        return this.findFilmList(null, null, null, null, null);
    }

    @Override
    public List<Film> findByFilter(FilmFilter filmFilter) throws MovieLibraryException {
        return this.findAll().stream()
                .filter(film -> film.getRating() >= filmFilter.getFromRating() && film.getRating()  <= filmFilter.getToRating())
                .filter(film -> film.getReleaseDate().isBefore(LocalDate.of(filmFilter.getToYear(), Month.JANUARY, 1))
                        && film.getReleaseDate().isAfter(LocalDate.of(filmFilter.getFromYear(), Month.JANUARY, 1)))
                .collect(Collectors.toList());

    }


    @Override
    public Review postReview(Film film, User user, Review review) throws MovieLibraryException {
        if (user.getRole() == Role.USER) {
            try {
                return filmRepository.saveReview(film, user, review);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new MovieLibraryException(MovieLibraryError.FORBIDDEN_FOR_CURRENT_USER);
        }
        return review;
    }

    @Override
    public List<Review> getUserReviews(User user) {
        try {
            return filmRepository.getUserReviews(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteReview(int id, User user) throws MovieLibraryException {
        if (user.getRole() == Role.USER) {
            try {
                filmRepository.deleteReview(user,id);
            } catch (Exception e) {
                throw new MovieLibraryException(MovieLibraryError.NOT_CORRECT_REVIEW_ID);
            }
        }
    }

    @Override
    public Review editReview(Film film,User user , Review editedReview) {
        if (user.getRole() == Role.USER) {
            try {
                filmRepository.updateReview(film,user,editedReview);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Film create(Film film) {
        return null;
    }

}
