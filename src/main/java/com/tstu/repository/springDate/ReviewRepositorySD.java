package com.tstu.repository.springDate;

import com.tstu.model.Film;
import com.tstu.model.Review;
import com.tstu.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepositorySD extends CrudRepository<Review,Long> {

    List<Review> findAllByAuthor(User author);

    List<Review> findAllByFilm(Film film);

    Optional<Review> findByAuthorAndFilm(User author, Film film);

    void deleteById(Long id);
}
