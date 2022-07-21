package ru.otus.spring.repository.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.model.Comment;
import ru.otus.spring.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Long getCount() {
        TypedQuery<Long> query = entityManager.createQuery("Select count(c) from Comment c", Long.class);
        Long count = query.getSingleResult();
        return count != null ? count : 0L;
    }

    @Override
    public Long getMaxCount() {
        TypedQuery<Long> query = entityManager.createQuery("Select max(c.id) from Comment c", Long.class);
        Long count = query.getSingleResult();
        return count != null ? count : 0L;
    }

    @Override
    public Comment findCommentById(Long commentId) {
        TypedQuery<Comment> query = entityManager.createQuery("Select c from Comment c where c.id = :id", Comment.class);
        query.setParameter("id", commentId);
        return query.getSingleResult();
    }

    @Override
    public List<Comment> findCommentByBookId(Long bookId) {
        TypedQuery<Comment> query = entityManager.createQuery(
                "Select c from Comment c where c.bookId = :id", Comment.class);
        query.setParameter("id", bookId);
        return query.getResultList();
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = entityManager.createQuery("Select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Comment add(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Transactional
    @Override
    public void delete(Comment comment) {
        Query query = entityManager.createQuery(
                "Delete from Comment c where c.id = :id");
        query.setParameter("id", comment.getId());
        query.executeUpdate();
    }
}
