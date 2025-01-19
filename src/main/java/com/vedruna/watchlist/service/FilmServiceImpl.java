package com.vedruna.watchlist.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.dto.entitiesdtos.FilmDTO;
import com.vedruna.watchlist.dto.mappers.FilmMapper;
import com.vedruna.watchlist.persistance.model.Film;
import com.vedruna.watchlist.persistance.repository.FilmRepositoryI;

@Service
public class FilmServiceImpl implements FilmServiceI {

    @Autowired
    private FilmRepositoryI filmRepository;

    @Autowired
    private FilmMapper filmMapper;

    @Override
    public Page<FilmDTO> selectAllFilms(Pageable pageable) {
        return filmMapper.pageEntitiesToDTOs(filmRepository.findAll(pageable));//filmRepository.findAll(pageable).map(FilmDTO::new);
    }

    @Override
    public FilmDTO selectFilmById(Integer filmId) {
        Optional<Film> film = filmRepository.findById(filmId);
        if (film.isPresent()) {
            return filmMapper.entityToDTO(film.get()); //new FilmDTO(film.get());
        } else {
            return null;
        }
    }

    //Pageable not working correctly??
    @Override
    public Page<FilmDTO> selectFilmsByReleaseDate(Date fromDate, Date toDate, Pageable pageable) {
        return filmMapper.pageEntitiesToDTOs(filmRepository.findByReleaseDateBetween(fromDate, toDate, pageable));
    }

    @Override
    public boolean insertFilm(FilmDTO filmDTO) {
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setReleaseDate(filmDTO.getReleaseDate());
        try {
            filmRepository.save(film);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean updateFilm(Integer filmId, FilmDTO filmDTO) {
        Optional<Film> possFilm = filmRepository.findById(filmId);

        if (possFilm.isPresent()) {
            Film film = possFilm.get();
            film.setTitle(filmDTO.getTitle());
            film.setReleaseDate(filmDTO.getReleaseDate());

            try {
                filmRepository.save(film);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
        
    }

    @Override
    public boolean deleteFilm(Integer filmId) {
        try {
            filmRepository.deleteById(filmId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Page<FilmDTO> selectFilmsByUserWatched(Integer userId, Pageable pageable) {
        return filmMapper.pageEntitiesToDTOs(filmRepository.findFilmByUser(userId, pageable));
    }
    
}
