package com.vedruna.watchlist.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.dto.UserDTO;
import com.vedruna.watchlist.persistance.model.User;
import com.vedruna.watchlist.persistance.repository.UserRepositoryI;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepositoryI userRepository;

    @Override
    public Page<UserDTO> selectAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::new);
    }

    @Override
    public UserDTO selectUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new UserDTO(user.get());
        } else {
            return null;
        }
    }

    @Override
    public UserDTO selectUserByUsername(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent()) {
            return new UserDTO(user.get());
        } else {
            return null;
        }
    }
    
}
