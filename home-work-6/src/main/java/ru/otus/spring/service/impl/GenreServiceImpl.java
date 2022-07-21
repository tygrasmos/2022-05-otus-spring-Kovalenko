package ru.otus.spring.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.ServiceHelper;
import ru.otus.spring.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final static String GENRE_IDENT = "genre";
    private final static String GENERATED_RECORD_ERROR = "error";

    private final GenreRepository genreRepository;
    private final ServiceHelper serviceHelper;

    public GenreServiceImpl(GenreRepository genreRepository,
                            ServiceHelper serviceHelper){
        this.genreRepository = genreRepository;
        this.serviceHelper = serviceHelper;
    }

    @Override
    public Long getCount() {
        return genreRepository.getCount();
    }

    @Override
    public Long getMaxCount() {
        return genreRepository.getMaxCount();
    }

    @Override
    public Genre findGenreById(Long genreId) {
        return genreRepository.findGenreById(genreId);
    }

    @Override
    public Genre findGenreByName(String genreName) {
        return genreRepository.findGenreByName(genreName);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public void addGenre(Genre genre) {
        if(!isNameMatch(genre.getGenreName())) {
          //  Long newCount = genreRepository.getMaxCount() + 1L;
          //  genre.setId(newCount);
            genreRepository.add(genre);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void changeGenre(Genre oldGenre, Genre newGenre) {
        if(isNameMatch(oldGenre.getGenreName())){
            Genre currentGenre = genreRepository.findGenreByName(oldGenre.getGenreName());
            currentGenre.setGenreName(newGenre.getGenreName());
            genreRepository.update(currentGenre);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteGenre(Genre genre) {
        if(isNameMatch(genre.getGenreName())){
            try{
                genreRepository.delete(genreRepository.findGenreByName(genre.getGenreName()));
            } catch (DataIntegrityViolationException e){
                throw new RuntimeException(GENERATED_RECORD_ERROR);
            }
        } else {
            throw new RuntimeException();
        }
    }

    private Boolean isNameMatch(String authorName){
        return serviceHelper.isNameMatch(authorName, GENRE_IDENT);
    }
}
