package com.vedruna.watchlist.service.cron;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vedruna.watchlist.dto.externalapidto.ExternalFilm;
import com.vedruna.watchlist.dto.externalapidto.FilmPage;
import com.vedruna.watchlist.persistance.model.Film;
import com.vedruna.watchlist.persistance.repository.FilmRepositoryI;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CronService {

    //mas info -> https://www.baeldung.com/spring-5-webclient
    private WebClient client = WebClient.create("https://api.themoviedb.org/3/");

    @Autowired
    private FilmRepositoryI filmRepository;

    @Value("${themoviedb.api.key}")
    private String bearerToken;

    //https://crontab.cronhub.io/
    @Scheduled(cron = "0 0 8 * * *")
    @Transactional
    void startCronJob() {
        cronJob();
    }

    public void cronJob() {
        log.info("Cron job executed. Updating films.");
        for (int page = 1; page <= 500; page++) {
            log.debug("Page: {}", page);
            FilmPage pageResult = client.get()
                            .uri("/discover/movie?include_adult=true&include_video=false&language=en-US&page=" + page + "&sort_by=popularity.desc")
                            .header("Authorization", "Bearer " + bearerToken)
                            .retrieve()
                            .bodyToMono(FilmPage.class)
                            .block();
            log.debug("Page result: {}", pageResult);
            for (ExternalFilm extFilm : pageResult.getResults()) {
                log.debug("Film: {}", extFilm);
                if (extFilm.getRelease_date() == null) {
                    log.debug("Film has not got release date: {}", extFilm);
                } else {
                    LocalDate today = LocalDate.now();
                    LocalDate extFilmDate = extFilm.getRelease_date().toLocalDate();
                    if (extFilmDate.isBefore(today) || extFilmDate.isEqual(today)) {
                        Film film = new Film();
                        film.setFilmId(extFilm.getId());
                        film.setTitle(extFilm.getTitle() + " (" + extFilm.getOriginal_language() + ")" + " (" + extFilmDate.getYear() + ")");
                        film.setReleaseDate(extFilm.getRelease_date());
                        Film savedFilm = filmRepository.save(film);
                        log.debug("Saved film: {}", savedFilm);
                    } else {
                        log.debug("Film has not released yet: {}", extFilm);
                    }
                }
            }
        }
        log.info("Cron job finished successfully.");
    }
}
