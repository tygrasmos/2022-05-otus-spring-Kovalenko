package ru.otus.spring.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.DaoService;

import java.util.List;

@Service

public class DaoServiceImpl implements DaoService {

    private final static String BOOK_IDENT = "book";
    private final static String AUTHOR_IDENT = "author";
    private final static String GENRE_IDENT = "genre";
    private final static String GENERATED_RECORD_ERROR = "error";

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public DaoServiceImpl(BookDao bookDao,
                          AuthorDao authorDao,
                          GenreDao genreDao){
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public void addBook(Book book) {
        String authorName = book.getAuthor().getAuthorName();
        String genreName = book.getGenre().getGenreName();
        if(!isNameMatch(book.getBookName(), BOOK_IDENT)) {
            if(isNameMatch(authorName, AUTHOR_IDENT)){
                book.setAuthor(authorDao.findAuthorByName(authorName));
                if(isNameMatch(genreName, GENRE_IDENT)){
                    book.setGenre(genreDao.findGenreByName(genreName));
                    Long newCount = bookDao.getMaxCount() + 1L;
                    book.setId(newCount);
                    bookDao.add(book);
                } else {
                    throw new RuntimeException(GENRE_IDENT);
                }
            } else {
                throw new RuntimeException(AUTHOR_IDENT);
            }
        } else {
            throw new RuntimeException(BOOK_IDENT);
        }
    }

    @Override
    public void changeBook(Book oldBook, Book newBook) {
        if(isNameMatch(oldBook.getBookName(), BOOK_IDENT)) {
            Book currentBook = bookDao.findBookByName(oldBook.getBookName());
            currentBook.setBookName(newBook.getBookName());
            bookDao.update(currentBook);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteBook(Book book) {
        if(isNameMatch(book.getBookName(), BOOK_IDENT)){
            bookDao.delete(bookDao.findBookByName(book.getBookName()));
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void addAuthor(Author author) {
        if(!isNameMatch(author.getAuthorName(), AUTHOR_IDENT)) {
            Long newCount = authorDao.getMaxCount() + 1L;
            author.setId(newCount);
            authorDao.add(author);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void changeAuthor(Author oldAuthor, Author newAuthor) {
        if(isNameMatch(oldAuthor.getAuthorName(), AUTHOR_IDENT)){
            Author currentAuthor = authorDao.findAuthorByName(oldAuthor.getAuthorName());
            currentAuthor.setAuthorName(newAuthor.getAuthorName());
            authorDao.update(currentAuthor);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        if(isNameMatch(author.getAuthorName(), AUTHOR_IDENT)){
            try{
                Author author1 = authorDao.findAuthorByName(author.getAuthorName());
                authorDao.delete(author1);
            } catch (DataIntegrityViolationException e){
                throw new RuntimeException(GENERATED_RECORD_ERROR);
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void addGenre(Genre genre) {
        if(!isNameMatch(genre.getGenreName(), GENRE_IDENT)) {
            Long newCount = genreDao.getMaxCount() + 1L;
            genre.setId(newCount);
            genreDao.add(genre);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void changeGenre(Genre oldGenre, Genre newGenre) {
        if(isNameMatch(oldGenre.getGenreName(), GENRE_IDENT)){
            Genre currentGenre = genreDao.findGenreByName(oldGenre.getGenreName());
            currentGenre.setGenreName(newGenre.getGenreName());
            genreDao.update(currentGenre);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteGenre(Genre genre) {
        if(isNameMatch(genre.getGenreName(), GENRE_IDENT)){
            try{
                genreDao.delete(genreDao.findGenreByName(genre.getGenreName()));
            } catch (DataIntegrityViolationException e){
                throw new RuntimeException(GENERATED_RECORD_ERROR);
            }
        } else {
            throw new RuntimeException();
        }
    }

    private Boolean isNameMatch(String name, String objectName){
        switch (objectName){
            case (BOOK_IDENT):
                for(Book book : bookDao.findAll()) {
                    if (book.getBookName().equals(name)) {
                        return true;
                    }
                }
                return false;
            case (AUTHOR_IDENT):
                for(Author author : authorDao.findAll()) {
                    if (author.getAuthorName().equals(name)) {
                        return true;
                    }
                }
                return false;
            case (GENRE_IDENT):
                for(Genre genre : genreDao.findAll()) {
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
