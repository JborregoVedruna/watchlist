package com.vedruna.watchlist.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.watchlist.dto.FilmDTO;
import com.vedruna.watchlist.dto.ResponseDTO;
import com.vedruna.watchlist.service.FilmServiceI;

@RestController
@RequestMapping("/api/v1/film")
@CrossOrigin
public class FilmController {

    @Autowired
    private FilmServiceI filmService;

    @GetMapping("/all")
    public ResponseEntity<Page<FilmDTO>> getAllFilms(Pageable pageable) {
        return ResponseEntity.ok().body(filmService.selectAllFilms(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable Integer id) {
        return null;
    }
    @GetMapping("/release_date/{date}")
    public ResponseEntity<Page<FilmDTO>> getFilmsByReleaseDate(@PathVariable Date date) {
        return ResponseEntity.ok().body(filmService.selectFilmsByReleaseDate(date, null));
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> postFilm() {
        return null;
    }
    @PutMapping("/")
    public ResponseEntity<ResponseDTO> putFilm() {
        return null;
    }
    @DeleteMapping("/")
    public ResponseEntity<ResponseDTO> deleteFilm() {
        return null;
    }

    @PostMapping("/watched/{filmId}")
    public ResponseEntity<ResponseDTO> checkFilm() {
        return null;
    }
    @DeleteMapping("/notwatched/{filmId}")
    public ResponseEntity<ResponseDTO> uncheckFilm(@PathVariable Integer filmId) {
        return null;
    }
    //Pelicula Vista
}
