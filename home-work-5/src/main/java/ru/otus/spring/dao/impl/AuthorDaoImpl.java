package ru.otus.spring.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.mapper.AuthorMapper;
import ru.otus.spring.model.Author;
import ru.otus.spring.dao.AuthorDao;

import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorMapper authorMapper;

    public AuthorDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                         AuthorMapper authorMapper){
        this.authorMapper = authorMapper;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Long getCount() {
        Long count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from authors", Long.class);
        return count != null ? count : 0L;
    }

    @Override
    public Long getMaxCount() {
        Long maxCount = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select max(a.id) from authors a", Long.class);
        return maxCount != null ? maxCount : 0L;
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from authors where id = :id", Map.of("id", authorId), authorMapper
        );
    }

    @Override
    public Author findAuthorByName(String authorName){
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from authors where name = :name", Map.of("name", authorName), authorMapper
        );
    }

    @Override
    public List<Author> findAll() {
        return namedParameterJdbcOperations.
                query("select id, name from authors", authorMapper);
    }

    @Override
    public void add(Author author) {
        namedParameterJdbcOperations.update("insert into authors (id, name) values (:id, :name)",
                Map.of("id", author.getId(), "name", author.getAuthorName()));
    }

    @Override
    public void update(Author author) {
        namedParameterJdbcOperations.update("update authors a set a.name = :name where a.id = :id",
                Map.of("id", author.getId(), "name", author.getAuthorName()));
    }

    @Override
    public void delete(Author author) {
        namedParameterJdbcOperations.update(
                "delete from authors where id = :id", Map.of("id", author.getId())
        );
    }

}
