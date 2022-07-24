package ru.otus.spring.repository.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import javax.persistence.*;
import java.util.List;


@Service
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Long getCount() {
        TypedQuery<Long> query = entityManager.createQuery("Select count(b) from Book b", Long.class);
        Long count = query.getSingleResult();
        return count != null ? count : 0L;
    }

    @Override
    public Long getMaxCount() {
        TypedQuery<Long> query = entityManager.createQuery("select max(b.id) from Book b", Long.class);
        Long maxCount = query.getSingleResult();
        return maxCount != null ? maxCount : 0L;
    }

    @Override
    public Book findBookById(Long bookId) {
        EntityGraph<?> entityGraphAuthors = entityManager.getEntityGraph("book-fields-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery(
                "Select b from Book b where b.id = :id", Book.class);
        query.setParameter("id", bookId);
        query.setHint("javax.persistence.fetchgraph", entityGraphAuthors);
        return query.getSingleResult();
    }

    @Override
    public Book findBookByName(String bookName) {
        EntityGraph<?> entityGraphAuthors = entityManager.getEntityGraph("book-fields-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery(
                "Select b from Book b where b.bookName = :name", Book.class);
        query.setParameter("name", bookName);
        query.setHint("javax.persistence.fetchgraph", entityGraphAuthors);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraphAuthors = entityManager.getEntityGraph("book-fields-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery(
                "Select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraphAuthors);
        return query.getResultList();

    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        EntityGraph<?> entityGraphAuthors = entityManager.getEntityGraph("book-fields-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery(
                "Select b from Book b where b.author = :author", Book.class);
        query.setParameter("author", author);
        query.setHint("javax.persistence.fetchgraph", entityGraphAuthors);
        return query.getResultList();
    }

    @Override
    public List<Book> findBooksByGenre(Genre genre) {
        EntityGraph<?> entityGraphAuthors = entityManager.getEntityGraph("book-fields-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery(
                "Select b from Book b where b.genre = :genre", Book.class);
        query.setParameter("genre", genre);
        query.setHint("javax.persistence.fetchgraph", entityGraphAuthors);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Book add(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Transactional
    @Override
    public void update(Book book) {
        Query query = entityManager.createQuery(
                "Update Book b set b.bookName = :name where b.id = :id");
        query.setParameter("name", book.getBookName());
        query.setParameter("id", book.getId());
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void delete(Book book) {
        Query query = entityManager.createQuery(
                "Delete from Book b where b.id = :id");
        query.setParameter("id", book.getId());
        query.executeUpdate();
    }
}
