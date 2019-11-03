package com.tstu.repository;

import com.tstu.model.Film;

import java.time.LocalDate;
import java.util.List;

public interface FilmRepository {
    Film findById(String imdbId) throws Exception;
    List<Film> findByName(String name) throws Exception;
    Film findByDate(LocalDate date) throws Exception;
}
