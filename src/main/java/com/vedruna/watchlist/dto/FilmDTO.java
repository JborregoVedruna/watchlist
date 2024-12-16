package com.vedruna.watchlist.dto;

import java.sql.Date;

import com.vedruna.watchlist.persistance.model.Film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FilmDTO {
    Integer filmId;
    String title;
    Date releaseDate;

    public FilmDTO(Film film) {
        this.filmId = film.getFilmId();
        this.title = film.getTitle();
        this.releaseDate = film.getReleaseDate();
    }
}
