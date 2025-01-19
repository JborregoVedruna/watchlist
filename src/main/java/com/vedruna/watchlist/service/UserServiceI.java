package com.vedruna.watchlist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.watchlist.dto.entitiesdtos.UserDTO;



public interface UserServiceI {
    Page<UserDTO> selectAllUsers(Pageable pageable);
    UserDTO selectUserById(Integer userId);
    Page<UserDTO> selectUserByUsernameStartingWith(String name, Pageable pageable);
}