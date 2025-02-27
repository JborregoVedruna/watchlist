package com.vedruna.watchlist.dto.externalapidto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class FilmPage {
    Integer page;
    ExternalFilm[] results;
    Integer total_pages;
    Integer total_results;
}
