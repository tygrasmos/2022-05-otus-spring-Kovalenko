package ru.otus.spring.service;

import ru.otus.spring.model.Genre;

import java.util.List;

public interface GenreService {

    Long getCount();

    Long getMaxCount();

    Genre findGenreById(Long genreId);

    Genre findGenreByName(String genreName);

    List<Genre> findAll();

    void addGenre(Genre genre);

    void changeGenre(Genre oldGenre, Genre newGenre);

    void deleteGenre(Genre genre);

}
