package ru.otus.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.repository.impl.AuthorRepositoryImpl;
import ru.otus.spring.repository.impl.BookRepositoryImpl;
import ru.otus.spring.repository.impl.GenreRepositoryImpl;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Метод текущего Dao :")
@SpringBootTest
public class ApplicationTest {

    private static final Long EXPECTED_BOOKS_COUNT = 6L;
    private static final Long EXISTING_AUTHORS_COUNT = 6L;
    private static final Long EXISTING_GENRES_COUNT = 6L;
    private static final Long TEST_ID_FOR_DELETING_OR_UPDATING = 6L;
    private static final Long TEST_ID_FOR_ADDED = 7L;

    @Autowired
    private BookRepositoryImpl bookDao;
    @Autowired
    private AuthorRepositoryImpl authorDao;
    @Autowired
    private GenreRepositoryImpl genreDao;

    @DisplayName("BookDao должен возвращать количество книг в библиотеке")
    @Test
    void shouldReturnTheNumberOfBooksInTheLibrary(){
        Long actualBooksCount = bookDao.getCount();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("AuthorDao должен возвращать количество авторов в библиотеке")
    @Test
    void shouldReturnTheNumberOfAuthorsInTheLibrary(){
        Long actualAuthorsCount = authorDao.getCount();
        assertThat(actualAuthorsCount).isEqualTo(EXISTING_AUTHORS_COUNT);
    }

    @DisplayName("GenreDao должен возвращать количество жанров литературы в библиотеке")
    @Test
    void shouldReturnTheNumberOfGenresInTheLibrary(){
        Long actualGenresCount = genreDao.getCount();
        assertThat(actualGenresCount).isEqualTo(EXISTING_GENRES_COUNT);
    }

    @DisplayName("BookDao должен возвращать список всех книг в библиотеке")
    @Test
    void shouldReturnAllBooks(){
        List<Book> resultBookList = bookDao.findAll();
        assertThat(resultBookList).usingRecursiveComparison().isEqualTo(getExistingBooks());
    }

    @DisplayName("AuthorDao должен возвращать список всех авторов в библиотеке")
    @Test
    void shouldReturnAllAuthors(){
        List<Author> resultAuthorList = authorDao.findAll();
        assertThat(resultAuthorList).usingRecursiveComparison().isEqualTo(getExistingAuthors());
    }

    @DisplayName("GenreDao должен возвращать список всх литературных жанров в библиотеке")
    @Test
    void shouldReturnAllGenres(){
        List<Genre> resultGenreList = genreDao.findAll();
        assertThat(resultGenreList).usingRecursiveComparison().isEqualTo(getExistingGenres());
    }

    @DisplayName("BookDao должен возвращать книгу по её id")
    @Test
    void shouldReturnTheBookById(){
        Book existingBook = getTestBook(5L, "Сказка о царе Салтане",
                getTestAuthor(1L, "Пушкин"),
                getTestGenre(4L, "Сказка"));
        Book newBook = bookDao.findBookById(5L);
        assertThat(newBook).usingRecursiveComparison().isEqualTo(existingBook);
    }

    @DisplayName("AuthorDao должен возвращать автора по его id")
    @Test
    void shouldReturnTheAuthorById(){
        assertThat(authorDao.findAuthorById(1L))
                .usingRecursiveComparison().isEqualTo(getTestAuthor(1L, "Пушкин"));
    }

    @DisplayName("GenreDao должен возвращать жанр по его id")
    @Test
    void shouldReturnTheGenreById(){
        assertThat(genreDao.findGenreById(4L))
                .usingRecursiveComparison().isEqualTo(getTestGenre(4L, "Сказка"));
    }

    @DisplayName("BookDao должен возвращать список книг по имени автора")
    @Test
    void shouldReturnTheBookByAuthorName(){
        List<Book> bookList = new ArrayList<>();
        Book existingBook = getTestBook(5L, "Сказка о царе Салтане",
                getTestAuthor(1L, "Пушкин"),
                getTestGenre(4L, "Сказка"));
        bookList.add(existingBook);
        List<Book> resultBookList = bookDao.findBooksByAuthor(getTestAuthor(1L, "Пушкин"));
        assertThat(resultBookList).usingRecursiveComparison().isEqualTo(bookList);
    }

    @DisplayName("BookDao должен возвращать список книг по наименованию литературного жанра")
    @Test
    void shouldReturnTheBookByGenreName(){
        List<Book> bookList = new ArrayList<>();
        Book existingBook = getTestBook(4L, "Где то там...",
                getTestAuthor(2L, "Салтыков-Щедрин"),
                getTestGenre(5L, "Рассказ"));
        bookList.add(existingBook);
        List<Book> resultBookList = bookDao.findBooksByGenre(getTestGenre(5L, "Рассказ"));
        assertThat(resultBookList).usingRecursiveComparison().isEqualTo(bookList);
    }

    @DisplayName("BookDao должен возвращать книгу по её названию")
    @Test
    void shouldReturnTheBookByName(){
        Book existingBook = getTestBook(5L, "Сказка о царе Салтане",
                getTestAuthor(1L, "Пушкин"),
                getTestGenre(4L, "Сказка"));
        Book newBook = bookDao.findBookByName("Сказка о царе Салтане");
        assertThat(newBook).usingRecursiveComparison().isEqualTo(existingBook);
    }

    @DisplayName("AuthorDao должен возвращать автора по его имени")
    @Test
    void shouldReturnTheAuthorByName(){
        assertThat(authorDao.findAuthorByName("Пушкин"))
                .usingRecursiveComparison().isEqualTo(getTestAuthor(1L, "Пушкин"));
    }

    @DisplayName("GenreDao должен возвращать литературный жанр по его наименованию")
    @Test
    void shouldReturnTheGenreByName(){
        assertThat(genreDao.findGenreByName("Сказка"))
                .usingRecursiveComparison().isEqualTo(getTestGenre(4L, "Сказка"));
    }

    @DisplayName("BookDao должен добавлять новую книгу в библиотеку")
    @Test
    void shouldInsertBookInTheLibrary(){
        Book newBook = getTestBook(TEST_ID_FOR_ADDED, "Садко",
                getTestAuthor(1L, "Пушкин"),
                getTestGenre(4L, "Сказка"));
        bookDao.add(newBook);
        Book addedBook = bookDao.findBookById(TEST_ID_FOR_ADDED);
        assertThat(newBook).usingRecursiveComparison().isEqualTo(addedBook);
    }

    @DisplayName("AuthorDao должен добавлять нового автора в библиотеку")
    @Test
    void shouldInsertAuthorInTheLibrary(){
        Author newAuthor = getTestAuthor(TEST_ID_FOR_ADDED, "Барто");
        authorDao.add(newAuthor);
        Author addedAuthor = authorDao.findAuthorById(TEST_ID_FOR_ADDED);
        assertThat(newAuthor).usingRecursiveComparison().isEqualTo(addedAuthor);
    }

    @DisplayName("GenreDao должен добавлять новый жанр литературы в библиотеку")
    @Test
    void shouldInsertGenreInTheLibrary(){
        Genre newGenre = getTestGenre(TEST_ID_FOR_ADDED, "Повесть");
        genreDao.add(newGenre);
        Genre addedGenre = genreDao.findGenreById(TEST_ID_FOR_ADDED);
        assertThat(newGenre).usingRecursiveComparison().isEqualTo(addedGenre);
    }

    @DisplayName("BookDao должен изменять книгу в библиотеке")
    @Test
    void shouldUpdateBookInTheLibrary(){
        Book currentBook = bookDao.findBookById(TEST_ID_FOR_DELETING_OR_UPDATING);
        currentBook.setBookName("Новая книга");
        bookDao.update(currentBook);
        assertThat(bookDao.findBookById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .usingRecursiveComparison().isEqualTo(currentBook);
    }

    @DisplayName("AuthorDao должен изменять автора в библиотеке")
    @Test
    void shouldUpdateAuthorInTheLibrary(){
        Author currentAuthor = authorDao.findAuthorById(TEST_ID_FOR_DELETING_OR_UPDATING);
        currentAuthor.setAuthorName("Маршак");
        authorDao.update(currentAuthor);
        assertThat(authorDao.findAuthorById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .usingRecursiveComparison().isEqualTo(currentAuthor);
    }

    @DisplayName("GenreDao должен изменять жанр литературы в библиотеке")
    @Test
    void shouldUpdateGenreInTheLibrary(){
        Genre currentGenre = genreDao.findGenreById(TEST_ID_FOR_DELETING_OR_UPDATING);
        currentGenre.setGenreName("Приключения");
        genreDao.update(currentGenre);
        assertThat(genreDao.findGenreById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .usingRecursiveComparison().isEqualTo(currentGenre);
    }

    @DisplayName("BookDao должен удалять книгу из библиотеки")
    @Test
    void shouldDeleteBookInTheLibrary(){
        assertThatCode(() -> bookDao.findBookById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .doesNotThrowAnyException();
        bookDao.delete(bookDao.findBookById(TEST_ID_FOR_DELETING_OR_UPDATING));
        assertThatThrownBy(() -> bookDao.findBookById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("AuthorDao должен удалять автора из библиотеки")
    @Test
    void shouldDeleteAuthorInTheLibrary(){
        assertThatCode(() -> authorDao.findAuthorById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .doesNotThrowAnyException();
        authorDao.delete(authorDao.findAuthorById(TEST_ID_FOR_DELETING_OR_UPDATING));
        assertThatThrownBy(() -> authorDao.findAuthorById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("GenreDao должен удалять жанр литературы из библиотеки")
    @Test
    void shouldDeleteGenreInTheLibrary(){
        assertThatCode(() -> genreDao.findGenreById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .doesNotThrowAnyException();
        genreDao.delete(genreDao.findGenreById(TEST_ID_FOR_DELETING_OR_UPDATING));
        assertThatThrownBy(() -> genreDao.findGenreById(TEST_ID_FOR_DELETING_OR_UPDATING))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    private Author getTestAuthor(Long id, String authorName){
        Author existingAuthor = new Author();
        existingAuthor.setId(id);
        existingAuthor.setAuthorName(authorName);
        return existingAuthor;
    }

    private Genre getTestGenre(Long id, String genreName){
        Genre existingGenre = new Genre();
        existingGenre.setId(id);
        existingGenre.setGenreName(genreName);
        return existingGenre;
    }

    private Book getTestBook(Long id, String bookName, Author author, Genre genre){
        Book book = new Book();
        book.setId(id);
        book.setBookName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        return book;
    }

    private List<Book> getExistingBooks(){
        List<Book> existingBookList = new ArrayList<>();
        Book existingBook = getTestBook(1L, "Девять принцев Амбера",
                getTestAuthor(4L, "Желязны"),
                getTestGenre(3L, "Фантастика"));
        existingBookList.add(existingBook);
        existingBook = getTestBook(2L, "Generation Пи",
                getTestAuthor(5L, "Пелевин"),
                getTestGenre(2L, "Проза"));
        existingBookList.add(existingBook);
        existingBook = getTestBook(3L, "Мцыри",
                getTestAuthor(3L, "Лермонтов"),
                getTestGenre(1L, "Стихи"));
        existingBookList.add(existingBook);
        existingBook = getTestBook(4L, "Где то там...",
                getTestAuthor(2L, "Салтыков-Щедрин"),
                getTestGenre(5L, "Рассказ"));
        existingBookList.add(existingBook);
        existingBook = getTestBook(5L, "Сказка о царе Салтане",
                getTestAuthor(1L, "Пушкин"),
                getTestGenre(4L, "Сказка"));
        existingBookList.add(existingBook);
        existingBook = getTestBook(6L, "Чапаев и пустота",
                getTestAuthor(5L, "Пелевин"),
                getTestGenre(2L, "Проза"));
        existingBookList.add(existingBook);
        return existingBookList;
    }

    private List<Genre> getExistingGenres(){
        List<Genre> existingGenreList = new ArrayList<>();
        Genre existingGenre = getTestGenre(1L, "Стихи");
        existingGenreList.add(existingGenre);
        existingGenre = getTestGenre(2L, "Проза");
        existingGenreList.add(existingGenre);
        existingGenre = getTestGenre(3L, "Фантастика");
        existingGenreList.add(existingGenre);
        existingGenre = getTestGenre(4L, "Сказка");
        existingGenreList.add(existingGenre);
        existingGenre = getTestGenre(5L, "Рассказ");
        existingGenreList.add(existingGenre);
        existingGenre = getTestGenre(6L, "Новелла");
        existingGenreList.add(existingGenre);
        return existingGenreList;
    }

    private List<Author> getExistingAuthors(){
        List<Author> existingAuthorList = new ArrayList<>();
        Author existingAuthor = getTestAuthor(1L, "Пушкин");
        existingAuthorList.add(existingAuthor);
        existingAuthor = getTestAuthor(2L, "Салтыков-Щедрин");
        existingAuthorList.add(existingAuthor);
        existingAuthor = getTestAuthor(3L, "Лермонтов");
        existingAuthorList.add(existingAuthor);
        existingAuthor = getTestAuthor(4L, "Желязны");
        existingAuthorList.add(existingAuthor);
        existingAuthor = getTestAuthor(5L, "Пелевин");
        existingAuthorList.add(existingAuthor);
        existingAuthor = getTestAuthor(6L, "Лесков");
        existingAuthorList.add(existingAuthor);
        return existingAuthorList;
    }
}
