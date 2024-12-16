package com.vedruna.watchlist.persistance.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vedruna.watchlist.persistance.model.User;

@Repository
public interface UserRepositoryI extends JpaRepository<User, Integer> {
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    Optional<User> findByUsername(String username);
} 