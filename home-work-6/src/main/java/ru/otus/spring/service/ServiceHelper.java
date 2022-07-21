package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

@Service
public class ServiceHelper {

    private final static String BOOK_IDENT = "book";
    private final static String AUTHOR_IDENT = "author";
    private final static String GENRE_IDENT = "genre";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public ServiceHelper(BookRepository bookRepository,
                         AuthorRepository authorRepository,
                         GenreRepository genreRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public Boolean isNameMatch(String name, String objectName){
        switch (objectName){
            case (BOOK_IDENT):
                for(Book book : bookRepository.findAll()) {
                    if (book.getBookName().equals(name)) {
                        return true;
                    }
                }
                return false;
            case (AUTHOR_IDENT):
                for(Author author : authorRepository.findAll()) {
                    if (author.getAuthorName().equals(name)) {
                        return true;
                    }
                }
                return false;
            case (GENRE_IDENT):
                for(Genre genre : genreRepository.findAll()) {
                    if (genre.getGenreName().equals(name)) {
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }
}
