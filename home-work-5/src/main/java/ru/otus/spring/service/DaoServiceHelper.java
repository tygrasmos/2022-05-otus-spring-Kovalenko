package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;


@Service
public class DaoServiceHelper {

    private final static String BOOK_IDENT = "book";
    private final static String AUTHOR_IDENT = "author";
    private final static String GENRE_IDENT = "genre";

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public DaoServiceHelper(BookDao bookDao,
                            AuthorDao authorDao,
                            GenreDao genreDao){
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    public Boolean isNameMatch(String name, String objectName){
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
