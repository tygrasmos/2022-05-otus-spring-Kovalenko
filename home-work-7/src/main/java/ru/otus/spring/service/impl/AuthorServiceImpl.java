package ru.otus.spring.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.model.Author;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.ServiceHelper;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHOR_IDENT = "author";
    private final static String GENERATED_RECORD_ERROR = "error";

    private final AuthorRepository authorRepository;
    private final ServiceHelper serviceHelper;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             ServiceHelper serviceHelper){
        this.authorRepository = authorRepository;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public Long getCount() {
        return authorRepository.getCount();
    }

  /*  @Override
    public Long getMaxCount() {
        return authorRepository.getMaxCount();
    }*/

    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId).get();
    }

    @Override
    public Author findAuthorByName(String authorName) {
        return authorRepository.findAuthorByAuthorName(authorName);
    }

    @Override
    public List<Author> findAll() {
        List<Author> authorList = new ArrayList<>();
        authorRepository.findAll().forEach(authorList::add);
        return authorList;
    }

    @Override
    public void addAuthor(Author author) {
        if(!isNameMatch(author.getAuthorName())) {
            authorRepository.save(author);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void changeAuthor(Author oldAuthor, Author newAuthor) {
        if(isNameMatch(oldAuthor.getAuthorName())){
            Author currentAuthor = authorRepository.findAuthorByAuthorName(oldAuthor.getAuthorName());
            currentAuthor.setAuthorName(newAuthor.getAuthorName());
            authorRepository.save(currentAuthor);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        if(isNameMatch(author.getAuthorName())){
            try{
                Author author1 = authorRepository.findAuthorByAuthorName(author.getAuthorName());
                authorRepository.delete(author1);
            } catch (DataIntegrityViolationException e){
                throw new RuntimeException(GENERATED_RECORD_ERROR);
            }
        } else {
            throw new RuntimeException();
        }
    }

    private Boolean isNameMatch(String authorName){
        return serviceHelper.isNameMatch(authorName, AUTHOR_IDENT);
    }
}
