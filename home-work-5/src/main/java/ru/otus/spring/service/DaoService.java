package ru.otus.spring.service;

import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface DaoService {

    List<Book> showAllBooks();

    List<Author> showAllAuthors();

    List<Genre> showAllGenres();

    List<Book> findBooksByAuthor(Author author);

    List<Book> findBooksByGenre(Genre genre);

    Author findAuthorByName(String authorName);

    Genre findGenreByName(String genreName);

    void addBook(Book book);

    void changeBook(Book oldBook, Book newBook);

    void deleteBook(Book book);

    void addAuthor(Author author);

    void changeAuthor(Author oldAuthor, Author newAuthor);

    void deleteAuthor(Author author);

    void addGenre(Genre genre);

    void changeGenre(Genre oldGenre, Genre newGenre);

    void deleteGenre(Genre genre);
}
