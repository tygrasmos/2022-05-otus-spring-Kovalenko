package ru.otus.spring.service;

import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentService {

    Long getCount();

    Long getMaxCount();

    Comment findCommentById(Long commentId);

    List<Comment> findCommentByBookId(Long bookId);

    List<Comment> findAll();

    Comment addComment(Comment comment, String bookName);

    void delete(Comment comment);

}
