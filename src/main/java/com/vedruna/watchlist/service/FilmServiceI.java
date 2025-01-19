package com.vedruna.watchlist.service;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.watchlist.dto.entitiesdtos.FilmDTO;

public interface FilmServiceI {
    Page<FilmDTO> selectAllFilms(Pageable pageable);
    FilmDTO selectFilmById(Integer filmId);
    Page<FilmDTO> selectFilmsByReleaseDate(Date fromDate, Date toDate, Pageable pageable);
    Page<FilmDTO> selectFilmsByUserWatched(Integer userId, Pageable pageable);
    boolean insertFilm(FilmDTO filmDTO);
    boolean updateFilm(Integer filmId, FilmDTO filmDTO);
    boolean deleteFilm(Integer filmId);
}
