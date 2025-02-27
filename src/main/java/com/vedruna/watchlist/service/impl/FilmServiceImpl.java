package com.vedruna.watchlist.service.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.dto.entitydto.EditTitleDTO;
import com.vedruna.watchlist.dto.entitydto.FilmDTO;
import com.vedruna.watchlist.dto.entitydto.UserIdDTO;
import com.vedruna.watchlist.exception.FilmNotFoundException;
import com.vedruna.watchlist.exception.UserNotFoundException;
import com.vedruna.watchlist.persistance.model.Film;
import com.vedruna.watchlist.persistance.model.User;
import com.vedruna.watchlist.persistance.repository.FilmRepositoryI;
import com.vedruna.watchlist.persistance.repository.UserRepositoryI;
import com.vedruna.watchlist.service.FilmServiceI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FilmServiceImpl implements FilmServiceI {

    @Autowired
    private FilmRepositoryI filmRepository;

    @Autowired
    private UserRepositoryI userRepository;

    @Override
    public Page<Film> selectAllFilms(Pageable pageable) {
        log.debug("Selecting all films with pageable: {}", pageable);
        Page<Film> films = filmRepository.findAll(pageable);
        log.debug("Selected films: {}", films);
        return films;
    }

    @Override
    public Film selectFilmById(Integer filmId) {
        Film film = filmRepository.findById(filmId)
                        .orElseThrow(() -> new FilmNotFoundException());
        log.debug("Selected film: {}", film);
        return film;
    }

    @Override
    public Page<Film> selectFilmsByReleaseDate(Date fromDate, Date toDate, Pageable pageable) {
        Page<Film> films = filmRepository.findByReleaseDateBetween(fromDate, toDate, pageable);
        log.debug("Selected films: {}", films);
        return films;
    }

    @Override
    public Film insertFilm(FilmDTO filmDTO) {
        log.debug("Inserting film: {}", filmDTO);
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setReleaseDate(filmDTO.getReleaseDate());
        log.debug("Film to save: {}", film);
        Film savedFilm = filmRepository.save(film);
        log.debug("Saved film: {}", savedFilm);
        return savedFilm;
    }

    @Override
    public Film updateFilm(Integer filmId, FilmDTO filmDTO) {
        log.debug("Updating film with id: {} with film: {}", filmId, filmDTO);
        Film film = filmRepository.findById(filmId)
                                    .orElseThrow(() -> new FilmNotFoundException());
        log.debug("Film to update: {}", film);
        film.setTitle(filmDTO.getTitle());
        film.setReleaseDate(filmDTO.getReleaseDate());
        log.debug("Film to save: {}", film);
        Film updatedFilm = filmRepository.save(film);
        log.debug("Saved film: {}", updatedFilm);
        return updatedFilm;
    }

    @Override
    public Film updateFilmTitle(Integer filmId, EditTitleDTO title) {
        log.debug("Updating film with id: {} with title: {}", filmId, title);
        Film film = filmRepository.findById(filmId)
                                    .orElseThrow(() -> new FilmNotFoundException());
        log.debug("Film to update: {}", film);
        film.setTitle(title.getTitle());
        log.debug("Film to save: {}", film);
        Film updatedFilm = filmRepository.save(film);  
        log.debug("Saved film: {}", updatedFilm);
        return updatedFilm;
    }

    @Override
    public void deleteFilm(Integer filmId) {
        log.debug("Deleting film with id: {}", filmId);
        filmRepository.deleteById(filmId);
    }

    @Override
    public Page<Film> selectFilmsByUserWatched(Integer userId, Pageable pageable) {
        log.debug("Selecting films watched by user with id: {} with pageable: {}", userId, pageable);
        Page<Film> films = filmRepository.findFilmByUser(userId, pageable);
        log.debug("Selected films: {}", films);
        return films;
    }

    @Override
    public Film markAsWatched(Integer filmId, UserIdDTO userId) {
        log.debug("Marking film with id: {} as watched by user with id: {}", filmId, userId);
        Film filmDB = filmRepository.findById(filmId)
                                    .orElseThrow(() -> new FilmNotFoundException());
        log.debug("Film to mark as watched: {}", filmDB);
        User userDB = userRepository.findById(userId.getUserId())
                                        .orElseThrow(() -> new UserNotFoundException()
                                        );
        log.debug("User to mark as watcher: {}", userDB);
        filmDB.getUsersWatchedThisFilm().add(userDB);
        userDB.getFilmsWatchedByThisUser().add(filmDB);
        Film updatedFilm = filmRepository.save(filmDB);
        log.debug("Updated film: {}", updatedFilm);
        return updatedFilm;
    }

    @Override
    public void markAsUnwatched(Integer filmId, UserIdDTO userId){
        log.debug("Marking film with id: {} as unwatched by user with id: {}", filmId, userId);
        Film filmDB = filmRepository.findById(filmId)
                                    .orElseThrow(() -> new FilmNotFoundException());
        log.debug("Film to mark as unwatched: {}", filmDB);
        User userDB = userRepository.findById(userId.getUserId())
                                        .orElseThrow(() -> new FilmNotFoundException());
        log.debug("User to mark as unwatcher: {}", userDB);
        filmDB.getUsersWatchedThisFilm().remove(userDB);
        userDB.getFilmsWatchedByThisUser().remove(filmDB);
        Film updatedFilm = filmRepository.save(filmDB);
        log.debug("Updated film: {}", updatedFilm);
    }
    
}
