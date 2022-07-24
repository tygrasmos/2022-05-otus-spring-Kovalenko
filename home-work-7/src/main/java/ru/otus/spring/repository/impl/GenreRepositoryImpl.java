package ru.otus.spring.repository.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Long getCount() {
        TypedQuery<Long> query = entityManager.createQuery("Select count(g) from Genre g", Long.class);
        Long count = query.getSingleResult();
        return count != null ? count : 0L;
    }

    @Override
    public Long getMaxCount() {
        TypedQuery<Long> query = entityManager.createQuery("Select max(g.id) from Genre g", Long.class);
        Long maxCount = query.getSingleResult();
        return maxCount != null ? maxCount : 0L;
    }

    @Override
    public Genre findGenreById(Long genreId) {
        TypedQuery<Genre> query = entityManager.createQuery(
                "Select g from Genre g where g.id = :id", Genre.class);
        query.setParameter("id", genreId);
        return query.getSingleResult();
    }

    @Override
    public Genre findGenreByName(String genreName) throws EmptyResultDataAccessException {
        TypedQuery<Genre> query = entityManager.createQuery(
                "Select g from Genre g where g.genreName = :name", Genre.class);
        query.setParameter("name", genreName);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery(
                "Select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Genre add(Genre genre) {
        if (genre.getId() == null) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Transactional
    @Override
    public void update(Genre genre) {
        Query query = entityManager.createQuery(
                "Update Genre g set g.genreName = :name where g.id = :id");
        query.setParameter("name", genre.getGenreName());
        query.setParameter("id", genre.getId());
        query.executeUpdate();
    }

    @Transactional
    @Override
    public void delete(Genre genre) {
        Query query = entityManager.createQuery(
                "Delete from Genre g where g.id = :id");
        query.setParameter("id", genre.getId());
        query.executeUpdate();
    }
}
