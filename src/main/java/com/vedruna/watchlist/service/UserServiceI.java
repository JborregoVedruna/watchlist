package com.vedruna.watchlist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vedruna.watchlist.dto.UserDTO;



public interface UserServiceI {
    Page<UserDTO> selectAllUsers(Pageable pageable);
    UserDTO selectUserById(Integer userId);
    UserDTO selectUserByUsername(String name);
}