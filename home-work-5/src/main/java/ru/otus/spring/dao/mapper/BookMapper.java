package ru.otus.spring.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class BookMapper implements RowMapper<Book>{

    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    BookMapper(AuthorDao authorDao, GenreDao genreDao){
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Author author = authorDao.findAuthorById(rs.getLong("author_id"));
        Genre genre = genreDao.findGenreById(rs.getLong("genre_id"));
        return createBook(id, name, author, genre);
    }

    private Book createBook(Long id, String name, Author author, Genre genre){
        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(genre);
        book.setBookName(name);
        book.setId(id);
        return book;
    }
}
