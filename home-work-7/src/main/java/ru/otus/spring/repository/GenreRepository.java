package ru.otus.spring.repository;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreRepository {

    Long getCount();

    Long getMaxCount();

    Genre findGenreById(Long genreId);

    Genre findGenreByName(String genreName);

    List<Genre> findAll();

    Genre add(Genre genre);

    void update(Genre genre);

    void delete(Genre genre);
}
