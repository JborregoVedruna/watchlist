package com.vedruna.watchlist.dto.externalapidto;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class ExternalFilm {
    Integer id;
    String title;
    Date release_date;
    String original_language;
}
