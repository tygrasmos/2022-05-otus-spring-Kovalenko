package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ServiceHelper;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final static String BOOK_IDENT = "book";
    private final static String AUTHOR_IDENT = "author";
    private final static String GENRE_IDENT = "genre";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ServiceHelper serviceHelper;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           ServiceHelper serviceHelper){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public Long getCount() {
        return bookRepository.getCount();
    }

    @Override
    public Long getMaxCount() {
        return bookRepository.getMaxCount();
    }

    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookByName(String bookName) {
        return bookRepository.findBookByName(bookName);
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findBooksByAuthor(author);
    }

    @Override
    public List<Book> findBooksByGenre(Genre genre) {
        return bookRepository.findBooksByGenre(genre);
    }

    @Override
    public void addBook(Book book) {
        String authorName = book.getAuthor().getAuthorName();
        String genreName = book.getGenre().getGenreName();
        if(!isNameMatch(book.getBookName(), BOOK_IDENT)) {
            if(isNameMatch(authorName, AUTHOR_IDENT)){
                book.setAuthor(authorRepository.findAuthorByName(authorName));
                if(isNameMatch(genreName, GENRE_IDENT)){
                    book.setGenre(genreRepository.findGenreByName(genreName));
                 //   Long newCount = bookRepository.getMaxCount() + 1L;
                 //   book.setId(newCount);
                    bookRepository.add(book);
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
            Book currentBook = bookRepository.findBookByName(oldBook.getBookName());
            currentBook.setBookName(newBook.getBookName());
            bookRepository.update(currentBook);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteBook(Book book) {
        if(isNameMatch(book.getBookName(), BOOK_IDENT)){
            bookRepository.delete(bookRepository.findBookByName(book.getBookName()));
        } else {
            throw new RuntimeException();
        }
    }

    private Boolean isNameMatch(String authorName, String objectIdent){
        return serviceHelper.isNameMatch(authorName, objectIdent);
    }
}
