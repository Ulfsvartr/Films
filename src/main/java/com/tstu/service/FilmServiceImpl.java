package com.tstu.service;

import com.tstu.exceptions.MovieLibraryError;
import com.tstu.exceptions.MovieLibraryException;
import com.tstu.filters.FilmFilter;
import com.tstu.model.Film;
import com.tstu.model.Genre;
import com.tstu.model.Review;
import com.tstu.model.User;
import com.tstu.model.enums.Role;
import com.tstu.repository.FilmRepository;
import com.tstu.repository.springDate.GenreRepositorySD;
import com.tstu.repository.springDate.ReviewRepositorySD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
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
    public Film findById(String imdbId) throws MovieLibraryException {
        Film film = filmRepository.findById(imdbId)
                .orElseThrow(() -> new MovieLibraryException(MovieLibraryError.FILM_NOT_FOUND));
        film.addReviews(getFilmReviews(film));
        return film;
    }

    @Override
    public List<Film> findFilmList(String name, String imdbId, String type, String genre, String releaseDate) throws MovieLibraryException {
        List<Film> filmList = filmRepository.findFilmList(name, imdbId, type, genre, releaseDate);
        for (Film f:filmList){
            f.addReviews(getFilmReviews(f));
        }
        if (!filmList.isEmpty()) {
            return filmList;
        } else {
            throw new MovieLibraryException(MovieLibraryError.FILM_NOT_FOUND);
        }
    }

    @Override
    public List<Film> findAll() throws MovieLibraryException {
        List<Film> filmList = this.findFilmList(null, null, null, null, null);
        for (Film f:filmList){
            f.addReviews(getFilmReviews(f));
        }
        return filmList;
    }

    @Override
    public List<Film> findByFilter(FilmFilter filmFilter) throws MovieLibraryException {
            List <Film> filmList = this.findAll().stream()
                .filter(film -> film.getRating() >= filmFilter.getFromRating() && film.getRating() <= filmFilter.getToRating())
                .filter(film -> film.getReleaseDate().isBefore(LocalDate.of(filmFilter.getToYear(), Month.JANUARY, 1))
                        && film.getReleaseDate().isAfter(LocalDate.of(filmFilter.getFromYear(), Month.JANUARY, 1)))
                .collect(Collectors.toList());
            for (Film f : filmList){
                f.addReviews(getFilmReviews(f));
            }
            return filmList;
    }

    @Autowired
    private ReviewRepositorySD reviewRepositorySD;
    @Autowired
    private GenreRepositorySD genreRepositorySD;

    @Override
    public void postReview(Film film, User user, Review review) throws MovieLibraryException {
        review.setAuthor(user);
        review.setFilm(film);
        if (!reviewExist(user, film)) {
            reviewRepositorySD.save(review);
//            try {
//                filmRepository.saveReview(film, user, review);
//            } catch (MovieLibraryException e) {
//                e.printStackTrace();
//            }
        } else {
            throw new MovieLibraryException(MovieLibraryError.FORBIDDEN_FOR_CURRENT_USER);
        }
    }

    @Override
    public List<Review> getUserReviews(User user) {
        return reviewRepositorySD.findAllByAuthor(user);
        //return filmRepository.getUserReviews(user);
    }

    private List<Review> getFilmReviews(Film film)
    {
        return reviewRepositorySD.findAllByFilm(film);
    }

    @Override
    public List<Review> getAllReviews() {
        return (List<Review>) reviewRepositorySD.findAll();
    }

    @Override
    public List<Genre> getAllGenre() {
        return (List<Genre>) genreRepositorySD.findAll();
    }

    private Boolean userReview(User user, long id) {
        for (Review r : getUserReviews(user)) {
            if (r.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteReview(long id, User user) throws MovieLibraryException {
        if (userReview(user, id) || user.getRole() == Role.ADMIN) {
            reviewRepositorySD.deleteById(id);
//            try {
//                filmRepository.deleteReview(id);
//            } catch (Exception e) {
//                throw new MovieLibraryException(MovieLibraryError.NOT_CORRECT_REVIEW_ID);
//            }
        }
    }

    private Boolean reviewExist(User user, Film film) {
        for (Review r : getFilmReviews(film)) {
            if (r.getAuthor().getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void editReview(Film film, User user, Review editedReview) throws MovieLibraryException {
        if (reviewExist(user, film) || user.getRole() == Role.ADMIN) {
            Review review = reviewRepositorySD.findByAuthorAndFilm(user,film)
                    .orElseThrow(() -> new MovieLibraryException(MovieLibraryError.REVIEW_NOT_FOUND));
            review.setText(editedReview.getText());
            review.setRating(editedReview.getRating());
            reviewRepositorySD.save(review);
            //filmRepository.updateReview(film, user, editedReview);
        }
    }

    @Override
    public Film create(Film film) {
        return null;
    }

}
