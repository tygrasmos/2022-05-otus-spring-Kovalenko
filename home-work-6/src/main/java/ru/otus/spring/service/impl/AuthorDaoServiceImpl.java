package ru.otus.spring.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.AuthorDaoService;
import ru.otus.spring.service.DaoServiceHelper;

import java.util.List;

@Service
public class AuthorDaoServiceImpl implements AuthorDaoService {

    private final static String AUTHOR_IDENT = "author";
    private final static String GENERATED_RECORD_ERROR = "error";

    private final AuthorDao authorDao;
    private final DaoServiceHelper daoServiceHelper;

    public AuthorDaoServiceImpl(AuthorDao authorDao,
                                DaoServiceHelper daoServiceHelper){
        this.authorDao = authorDao;
        this.daoServiceHelper = daoServiceHelper;
    }

    @Override
    public Long getCount() {
        return authorDao.getCount();
    }

    @Override
    public Long getMaxCount() {
        return authorDao.getMaxCount();
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return authorDao.findAuthorById(authorId);
    }

    @Override
    public Author findAuthorByName(String authorName) {
        return authorDao.findAuthorByName(authorName);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public void addAuthor(Author author) {
        if(!isNameMatch(author.getAuthorName())) {
            Long newCount = authorDao.getMaxCount() + 1L;
            author.setId(newCount);
            authorDao.add(author);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void changeAuthor(Author oldAuthor, Author newAuthor) {
        if(isNameMatch(oldAuthor.getAuthorName())){
            Author currentAuthor = authorDao.findAuthorByName(oldAuthor.getAuthorName());
            currentAuthor.setAuthorName(newAuthor.getAuthorName());
            authorDao.update(currentAuthor);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        if(isNameMatch(author.getAuthorName())){
            try{
                Author author1 = authorDao.findAuthorByName(author.getAuthorName());
                authorDao.delete(author1);
            } catch (DataIntegrityViolationException e){
                throw new RuntimeException(GENERATED_RECORD_ERROR);
            }
        } else {
            throw new RuntimeException();
        }
    }

    private Boolean isNameMatch(String authorName){
        return daoServiceHelper.isNameMatch(authorName, AUTHOR_IDENT);
    }
}
