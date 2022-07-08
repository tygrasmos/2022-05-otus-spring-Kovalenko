package ru.otus.spring.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.mapper.BookMapper;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final BookMapper bookMapper;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations,
                       BookMapper bookMapper){
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.bookMapper = bookMapper;
    }

    @Override
    public Long getCount() {
        Long count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from books", Long.class);
        return count != null ? count : 0L;
    }

    @Override
    public Long getMaxCount() {
        Long maxCount = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select max(b.id) from books b", Long.class);
        return maxCount != null ? maxCount : 0L;
    }

    @Override
    public Book findBookById(Long bookId) {
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, author_id, genre_id from books where id = :id", Map.of("id", bookId), bookMapper
        );
    }

    @Override
    public Book findBookByName(String bookName) {
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, author_id, genre_id from books where name = :name", Map.of("name", bookName), bookMapper
        );
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcOperations.
                query("select id, name, author_id, genre_id from books", bookMapper);
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        return namedParameterJdbcOperations.query(
                    "select b.id, b.name, b.author_id, b.genre_id, a.name author_name, g.name genre_name\n" +
                        "  from books b\n" +
                        "  left join authors a on a.id = b.author_id\n" +
                        "  left join genres g on g.id = b.genre_id\n" +
                        " where b.author_id = :author_id", Map.of("author_id", author.getId()), bookMapper
        );
    }

    @Override
    public List<Book> findBooksByGenre(Genre genre) {
        return namedParameterJdbcOperations.query(
                    "select b.id, b.name, b.author_id, b.genre_id, a.name author_name, g.name genre_name\n" +
                        "  from books b\n" +
                        "  left join authors a on a.id = b.author_id\n" +
                        "  left join genres g on g.id = b.genre_id\n" +
                        " where b.genre_id = :genre_id", Map.of("genre_id", genre.getId()), bookMapper
        );
    }

    @Override
    public void add(Book book) {
        namedParameterJdbcOperations.update("insert into books (id, name, author_id, genre_id) values (:id, :name, :author_id, :genre_id)",
                Map.of("id", book.getId(), "name", book.getBookName()
                        , "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId()));
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("update books b set b.name = :name, b.author_id = :author_id, b.genre_id = :genre_id where b.id = :id",
                Map.of("id", book.getId(), "name", book.getBookName()
                        , "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId()));
    }

    @Override
    public void delete(Book book) {
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", Map.of("id", book.getId())
        );
    }
}
