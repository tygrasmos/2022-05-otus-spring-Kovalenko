package ru.otus.spring.repository;

import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentRepository {

    Long getCount();

    Long getMaxCount();

    Comment findCommentById(Long commentId);

    List<Comment> findCommentByBookId(Long bookId);

    List<Comment> findAll();

    Comment add(Comment comment);

    void delete(Comment comment);
}
