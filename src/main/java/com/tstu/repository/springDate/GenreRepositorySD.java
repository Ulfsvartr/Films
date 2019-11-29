package com.tstu.repository.springDate;

import com.tstu.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepositorySD extends CrudRepository<Genre,Long> {
}
