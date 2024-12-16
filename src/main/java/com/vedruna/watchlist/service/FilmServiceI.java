package com.vedruna.watchlist.service;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.watchlist.dto.FilmDTO;

public interface FilmServiceI {
    Page<FilmDTO> selectAllFilms(Pageable pageable);
    FilmDTO selectFilmById(Integer filmId);
    Page<FilmDTO> selectFilmsByReleaseDate(Date date, Pageable pageable);
    String insertFilm(FilmDTO filmDTO);
    String updateFilm(FilmDTO filmDTO);
    String deleteFilm(Integer filmId);
}
