package ru.otus.spring.dao;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorDao {

    Long getCount();

    Long getMaxCount();

    Author findAuthorById(Long authorId);

    Author findAuthorByName(String authorName);

    List<Author> findAll();

    void add(Author author);

    void update(Author author);

    void delete(Author author);
}
