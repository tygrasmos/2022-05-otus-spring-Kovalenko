package ru.otus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

   /* Long getCount();

    Long getMaxCount();

    Author findAuthorById(Long authorId);

    Author findAuthorByName(String authorName);

    List<Author> findAll();

    Author add(Author author);

    void update(Author author);

    void delete(Author author);*/

    Long getCount();

    Author findAuthorByAuthorName(String authorName);

   // List<Author> findAll();
}
