package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.BookDaoService;
import ru.otus.spring.service.DaoServiceHelper;

import java.util.List;

@Service
public class BookDaoServiceImpl implements BookDaoService {

    private final static String BOOK_IDENT = "book";
    private final static String AUTHOR_IDENT = "author";
    private final static String GENRE_IDENT = "genre";

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final DaoServiceHelper daoServiceHelper;

    public BookDaoServiceImpl(BookDao bookDao,
                          AuthorDao authorDao,
                          GenreDao genreDao,
                          DaoServiceHelper daoServiceHelper){
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.daoServiceHelper = daoServiceHelper;
    }

    @Override
    public Long getCount() {
        return bookDao.getCount();
    }

    @Override
    public Long getMaxCount() {
        return bookDao.getMaxCount();
    }

    @Override
    public Book findBookById(Long bookId) {
        return bookDao.findBookById(bookId);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findBookByName(String bookName) {
        return bookDao.findBookByName(bookName);
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        return bookDao.findBooksByAuthor(author);
    }

    @Override
    public List<Book> findBooksByGenre(Genre genre) {
        return bookDao.findBooksByGenre(genre);
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

    private Boolean isNameMatch(String authorName, String objectIdent){
        return daoServiceHelper.isNameMatch(authorName, objectIdent);
    }
}
