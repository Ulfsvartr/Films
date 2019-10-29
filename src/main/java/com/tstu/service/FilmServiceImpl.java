package com.tstu.service;

import com.tstu.model.*;
import com.tstu.repository.FilmRepository;
import com.tstu.repository.FilmRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository = FilmRepositoryImpl.getInstance();

    @Override
    public Film findById(String imdbId) throws Exception {
        return filmRepository.findById(imdbId);
    }

    @Override
    public List<Film> findByName(String name) throws Exception {
        List<Film> filmList = filmRepository.findByName(name);
        if(!filmList.isEmpty()){
            return filmRepository.findByName(name);
        }
        else{
            throw new Exception("Фильм не найден!");
        }
    }

    @Override
    public Film findByDate(LocalDate date) throws Exception {
        return filmRepository.findByDate(date);
    }

    @Override
    public Review postReview(Film film, User user, String text, int rating) throws Exception {
        if (user.getRole() == Role.USER) {
            Review review = new Review(user, text, rating);
            film.addReview(review);
            return review;
        } else {
            throw new Exception("Недоступно для данного типа пользователя.");
        }
    }

    @Override
    public void deleteReview(Film film, int id, User admin) throws Exception {
        if (admin.getRole() == Role.ADMIN) {
            try {
                film.getReviews().remove(id - 1);
                film.calculateAverageRating();
            } catch (Exception e) {
                throw new Exception("Невенрный id для удаления отзыва.");
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

    @Override
    public String showFilmDetails(Film film) {
        String filmDetail = "Название фильма: " + film.getName();
        filmDetail += "\nIMDB: " + film.getImdbId();
        filmDetail += "\nТип фильма: " + film.getType().getValue();
        filmDetail += "\nЖанр: " + film.getGenre().stream()
                .map(Genre::getValue)
                .collect(Collectors.joining(", "));
        filmDetail += "\nДата выхода: " + film.getReleaseDate();
        filmDetail += "\nРэйтинг: " + film.getRating();
        return filmDetail;
    }

    @Override
    public String showFilmReviews(Film film) {
        StringBuilder reviews = new StringBuilder();
        reviews.append("Название фильма: ").append(film.getName()).append("\nРейтинг: ").append(film.getRating()).append("\nОтзывы:\n");
        int id = 1;
        for (Review r : film.getReviews()) {
            reviews.append(id++).append(") ").append(r.getAuthor().getUsername()).append("\n").append(r.getText()).append("\nОценка пользователя: ").append(r.getRating()).append("\n");
        }
        return reviews.toString();
    }
}
