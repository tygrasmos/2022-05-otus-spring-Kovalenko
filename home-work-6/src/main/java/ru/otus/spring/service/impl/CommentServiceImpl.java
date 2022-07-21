package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.model.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.ServiceHelper;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final static String BOOK_IDENT = "book";

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final ServiceHelper serviceHelper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              BookRepository bookRepository,
                              ServiceHelper serviceHelper){
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public Long getCount() {
        return commentRepository.getCount();
    }

    @Override
    public Long getMaxCount() {
        return commentRepository.getMaxCount();
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findCommentById(commentId);
    }

    @Override
    public List<Comment> findCommentByBookId(Long bookId) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment addComment(Comment comment, String bookName) {
        if(isNameMatch(bookName)) {
            Long bookId = bookRepository.findBookByName(bookName).getId();
          //  Long newCount = commentRepository.getMaxCount() + 1L;
          //  comment.setId(null);
            comment.setBookId(bookId);
            commentRepository.add(comment);
            return comment;
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(Comment comment) {
        Comment comment1 = commentRepository.findCommentById(comment.getId());
        commentRepository.delete(comment1);
    }

    private Boolean isNameMatch(String bookName){
        return serviceHelper.isNameMatch(bookName, BOOK_IDENT);
    }

}
