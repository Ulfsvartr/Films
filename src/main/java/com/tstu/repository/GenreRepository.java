package com.tstu.repository;

import com.tstu.model.Genre;

import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findByName(String name);
}
