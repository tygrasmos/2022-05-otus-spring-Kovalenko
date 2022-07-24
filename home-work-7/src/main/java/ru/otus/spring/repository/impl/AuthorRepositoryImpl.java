package ru.otus.spring.repository.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.repository.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class AuthorRepositoryImpl /*implements AuthorRepository */{

  /*  @PersistenceContext
    private final EntityManager entityManager;

    public AuthorRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Long getCount() {
        TypedQuery<Long> query = entityManager.createQuery("Select count(a) from Author a", Long.class);
        Long count = query.getSingleResult();
        return count != null ? count : 0L;
    }

    @Override
    public Long getMaxCount() {
        TypedQuery<Long> query = entityManager.createQuery("Select max(a.id) from Author a", Long.class);
        Long count = query.getSingleResult();
        return count != null ? count : 0L;
    }

    @Override
    public Author findAuthorById(Long authorId) {
        // Попробовать другой вариант
        TypedQuery<Author> query = entityManager.createQuery("Select a from Author a where a.id = :id", Author.class);
        query.setParameter("id", authorId);
        return query.getSingleResult();

    }

    @Override
    public Author findAuthorByName(String authorName){
        TypedQuery<Author> query = entityManager.createQuery("Select a from Author a where a.authorName = :name", Author.class);
        query.setParameter("name", authorName);
        return query.getSingleResult();
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("Select a from Author a", Author.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Author add(Author author) {
        if (author.getId() == null) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Transactional
    @Override
    public void update(Author author) {
        Query query = entityManager.createQuery(
                "Update Author a set a.authorName = :name where a.id = :id");
        query.setParameter("name", author.getAuthorName());
        query.setParameter("id", author.getId());
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void delete(Author author) {
        Query query = entityManager.createQuery(
                "Delete from Author a where a.id = :id");
        query.setParameter("id", author.getId());
        query.executeUpdate();
    }*/

}
