package ru.otus.spring.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.dao.mapper.GenreMapper;
import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final GenreMapper genreMapper;

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                        GenreMapper genreMapper){
        this.genreMapper = genreMapper;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Long getCount() {
        Long count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from genres", Long.class);
        return count != null ? count : 0L;
    }

    @Override
    public Long getMaxCount() {
        Long maxCount = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select max(g.id) from genres g", Long.class);
        return maxCount != null ? maxCount : 0L;
    }

    @Override
    public Genre findGenreById(Long genreId) {
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from genres where id = :id", Map.of("id", genreId), genreMapper
        );
    }

    @Override
    public Genre findGenreByName(String genreName) throws EmptyResultDataAccessException {
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from genres where name = :name", Map.of("name", genreName), genreMapper
        );
    }

    @Override
    public List<Genre> findAll() {
        return namedParameterJdbcOperations.
                query("select id, name from genres", genreMapper);
    }

    @Override
    public void add(Genre genre) {
        namedParameterJdbcOperations.update("insert into genres (id, name) values (:id, :name)",
                Map.of("id", genre.getId(), "name", genre.getGenreName()));
    }

    @Override
    public void update(Genre genre) {
        namedParameterJdbcOperations.update("update genres g set g.name = :name where g.id = :id",
                Map.of("id", genre.getId(), "name", genre.getGenreName()));
    }

    @Override
    public void delete(Genre genre) {
        namedParameterJdbcOperations.update(
                "delete from genres where id = :id", Map.of("id", genre.getId())
        );
    }
}
