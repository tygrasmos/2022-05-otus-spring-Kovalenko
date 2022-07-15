package ru.otus.spring.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        Author author = new Author();
        author.setId(id);
        author.setAuthorName(name);
        return author;
    }
}