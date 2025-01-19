package com.vedruna.watchlist.dto.mappers;

import org.mapstruct.Mapper;

import com.vedruna.watchlist.dto.entitiesdtos.FilmDTO;
import com.vedruna.watchlist.persistance.model.Film;

@Mapper(componentModel = "spring")
public interface FilmMapper extends BaseMapper<Film, FilmDTO> {
    
}
