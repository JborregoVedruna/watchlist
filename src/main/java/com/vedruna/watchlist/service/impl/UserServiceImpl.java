package com.vedruna.watchlist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.exception.UserNotFoundException;
import com.vedruna.watchlist.persistance.model.User;
import com.vedruna.watchlist.persistance.repository.UserRepositoryI;
import com.vedruna.watchlist.service.UserServiceI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepositoryI userRepository;

    @Override
    public Page<User> selectAllUsers(Pageable pageable) {
        log.debug("Selecting all users with pageable: {}", pageable);
        Page<User> users = userRepository.findAll(pageable);
        log.debug("Selected users: {}", users);
        return users;
    }

    @Override
    public User selectUserById(Integer userId) {
        log.debug("Selecting user with id: {}", userId);
        User user = userRepository.findById(userId)
                                .orElseThrow(() -> new UserNotFoundException());
        log.debug("Selected user: {}", user);
        return user;
    }

    @Override
    public User selectUserByUsername(String username){
        log.debug("Selecting user with username: {}", username);
        User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new EmptyResultDataAccessException(
                                    "User not found", 
                            1));
        log.debug("Selected user: {}", user);
        return user;
    }

    @Override
    public Page<User> selectUserByUsernameStartingWith(String name, Pageable pageable) {
        log.debug("Selecting users by username starting with: {} with pageable: {}", name, pageable);
        Page<User> users = userRepository.findByUsernameStartingWith(name, pageable);
        log.debug("Selected users: {}", users);
        return users;
    }
    
}
