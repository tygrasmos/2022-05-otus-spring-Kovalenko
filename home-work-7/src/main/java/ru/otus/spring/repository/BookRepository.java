package ru.otus.spring.repository;

import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface BookRepository {

    Long getCount();

    Long getMaxCount();

    Book findBookById(Long bookId);

    List<Book> findAll();

    Book findBookByName(String bookName);

    List<Book> findBooksByAuthor(Author author);

    List<Book> findBooksByGenre(Genre genre);

    Book add(Book book);

    void update(Book book);

    void delete(Book book);
}
