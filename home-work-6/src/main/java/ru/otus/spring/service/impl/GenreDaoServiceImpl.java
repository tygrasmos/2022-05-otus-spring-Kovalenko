package ru.otus.spring.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.DaoServiceHelper;
import ru.otus.spring.service.GenreDaoService;

import java.util.List;

@Service
public class GenreDaoServiceImpl implements GenreDaoService {

    private final static String GENRE_IDENT = "genre";
    private final static String GENERATED_RECORD_ERROR = "error";

    private final GenreDao genreDao;
    private final DaoServiceHelper daoServiceHelper;

    public GenreDaoServiceImpl(GenreDao genreDao,
                                DaoServiceHelper daoServiceHelper){
        this.genreDao = genreDao;
        this.daoServiceHelper = daoServiceHelper;
    }

    @Override
    public Long getCount() {
        return genreDao.getCount();
    }

    @Override
    public Long getMaxCount() {
        return genreDao.getMaxCount();
    }

    @Override
    public Genre findGenreById(Long genreId) {
        return genreDao.findGenreById(genreId);
    }

    @Override
    public Genre findGenreByName(String genreName) {
        return genreDao.findGenreByName(genreName);
    }

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    @Override
    public void addGenre(Genre genre) {
        if(!isNameMatch(genre.getGenreName())) {
            Long newCount = genreDao.getMaxCount() + 1L;
            genre.setId(newCount);
            genreDao.add(genre);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void changeGenre(Genre oldGenre, Genre newGenre) {
        if(isNameMatch(oldGenre.getGenreName())){
            Genre currentGenre = genreDao.findGenreByName(oldGenre.getGenreName());
            currentGenre.setGenreName(newGenre.getGenreName());
            genreDao.update(currentGenre);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteGenre(Genre genre) {
        if(isNameMatch(genre.getGenreName())){
            try{
                genreDao.delete(genreDao.findGenreByName(genre.getGenreName()));
            } catch (DataIntegrityViolationException e){
                throw new RuntimeException(GENERATED_RECORD_ERROR);
            }
        } else {
            throw new RuntimeException();
        }
    }

    private Boolean isNameMatch(String authorName){
        return daoServiceHelper.isNameMatch(authorName, GENRE_IDENT);
    }
}
