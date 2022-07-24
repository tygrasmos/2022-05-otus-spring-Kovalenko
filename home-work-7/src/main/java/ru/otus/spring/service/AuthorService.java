package ru.otus.spring.service;

import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorService {

    Long getCount();

  //  Long getMaxCount();

    Author findAuthorById(Long authorId);

    Author findAuthorByName(String authorName);

    List<Author> findAll();

    void addAuthor(Author author);

    void changeAuthor(Author oldAuthor, Author newAuthor);

    void deleteAuthor(Author author);

}
