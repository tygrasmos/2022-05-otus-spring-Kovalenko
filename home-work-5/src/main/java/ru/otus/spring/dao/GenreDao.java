package ru.otus.spring.dao;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreDao {

    Long getCount();

    Long getMaxCount();

    Genre findGenreById(Long genreId);

    Genre findGenreByName(String genreName);

    List<Genre> findAll();

    void add(Genre genre);

    void update(Genre genre);

    void delete(Genre genre);
}
