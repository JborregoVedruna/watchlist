package com.vedruna.watchlist.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.dto.FilmDTO;
import com.vedruna.watchlist.persistance.model.Film;
import com.vedruna.watchlist.persistance.repository.FilmRepositoryI;

@Service
public class FilmServiceImpl implements FilmServiceI {

    @Autowired
    private FilmRepositoryI filmRepository;

    @Override
    public Page<FilmDTO> selectAllFilms(Pageable pageable) {
        return filmRepository.findAll(pageable).map(FilmDTO::new);
    }

    @Override
    public FilmDTO selectFilmById(Integer filmId) {
        Optional<Film> film = filmRepository.findById(filmId);
        if (film.isPresent()) {
            return new FilmDTO(film.get());
        } else {
            return null;
        }
    }

    //Pageable not working correctly??
    @Override
    public Page<FilmDTO> selectFilmsByReleaseDate(Date date, Pageable pageable) {
        return filmRepository.findByReleaseDate(date, pageable).map(FilmDTO::new);
    }

    @Override
    public String insertFilm(FilmDTO filmDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertFilm'");
    }

    @Override
    public String updateFilm(FilmDTO filmDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFilm'");
    }

    @Override
    public String deleteFilm(Integer filmId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFilm'");
    }
    
}
