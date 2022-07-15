package ru.otus.spring.service;

import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface BookDaoService {

    Long getCount();

    Long getMaxCount();

    Book findBookById(Long bookId);

    List<Book> findAll();

    Book findBookByName(String bookName);

    List<Book> findBooksByAuthor(Author author);

    List<Book> findBooksByGenre(Genre genre);

    void addBook(Book book);

    void changeBook(Book oldBook, Book newBook);

    void deleteBook(Book book);

}
