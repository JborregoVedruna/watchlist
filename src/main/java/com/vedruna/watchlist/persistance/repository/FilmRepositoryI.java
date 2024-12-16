package com.vedruna.watchlist.persistance.repository;
import java.sql.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.watchlist.persistance.model.Film;

@Repository
public interface FilmRepositoryI extends JpaRepository<Film, Integer> {
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    Page<Film> findByReleaseDate(Date date, Pageable pageable);
} 
