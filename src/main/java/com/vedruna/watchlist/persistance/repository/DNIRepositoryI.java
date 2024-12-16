package com.vedruna.watchlist.persistance.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.watchlist.persistance.model.DNI;

@Repository
public interface DNIRepositoryI extends JpaRepository<DNI, Integer> {
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
} 
