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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.watchlist.dto.ResponseDTO;
import com.vedruna.watchlist.dto.entitiesdtos.FilmDTO;
import com.vedruna.watchlist.service.FilmServiceI;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/film")
@CrossOrigin
public class FilmController {

    @Autowired
    private FilmServiceI filmService;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> postFilm(@RequestBody FilmDTO filmDTO) {
        boolean b = filmService.insertFilm(filmDTO);
        if (b) {
            return ResponseEntity.ok(new ResponseDTO("Pelicula insertada correctamente"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("No se ha podido insertar la pelicula"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<FilmDTO>> getAllFilms(Pageable pageable) {
        return ResponseEntity.ok().body(filmService.selectAllFilms(pageable));
    }

    @GetMapping("/release_date")
    public ResponseEntity<Page<FilmDTO>> getFilmsByReleaseDate(@RequestParam("from") Date fromDate, @RequestParam("to")Date toDate, Pageable pageable) {
        return ResponseEntity.ok().body(filmService.selectFilmsByReleaseDate(fromDate, toDate, pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable Integer id) {
        FilmDTO film = filmService.selectFilmById(id);
        if (film != null) {
            return ResponseEntity.ok().body(film);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> putFilm(@PathVariable Integer id, @RequestBody FilmDTO filmDTO) {
        boolean b = filmService.updateFilm(id, filmDTO);
        if (b) {
            return ResponseEntity.ok(new ResponseDTO("Pelicula editada correctamente"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("No se ha podido editar la pelicula"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteFilm(@PathVariable Integer id) {
        boolean b = filmService.deleteFilm(id);
        if (b) {
            return ResponseEntity.ok(new ResponseDTO("Pelicula eliminada correctamente"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("No se ha podido eliminar la pelicula"));
        }
    }

    @GetMapping("/user/{id:\\d+}")
    public ResponseEntity<Page<FilmDTO>> getFilmsByUserWatched(@PathVariable Integer id, Pageable pageable) {
        return ResponseEntity.ok().body(filmService.selectFilmsByUserWatched(id, pageable));
    }

    @PostMapping("/watched/{filmId}")
    public ResponseEntity<ResponseDTO> checkFilm(@PathVariable Integer filmId) {
        return null; //Lo implementaremos mas adelante
    }
    @DeleteMapping("/notwatched/{filmId}")
    public ResponseEntity<ResponseDTO> uncheckFilm(@PathVariable Integer filmId) {
        return null; //Lo implementaremos mas adelante
    }

}
