package com.vedruna.watchlist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.dto.entitiesdtos.UserDTO;
import com.vedruna.watchlist.dto.mappers.UserMapper;
import com.vedruna.watchlist.persistance.model.User;
import com.vedruna.watchlist.persistance.repository.UserRepositoryI;

@Service
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepositoryI userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserDTO> selectAllUsers(Pageable pageable) {
        return userMapper.pageEntitiesToDTOs(userRepository.findAll(pageable)); //userRepository.findAll(pageable).map(UserDTO::new);
    }

    @Override
    public UserDTO selectUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return userMapper.entityToDTO(user.get());//new UserDTO(user.get());
        } else {
            return null;
        }
    }

    @Override
    public Page<UserDTO> selectUserByUsernameStartingWith(String name, Pageable pageable) {
        Page<User> users = userRepository.findByUsernameStartingWith(name, pageable);
        return userMapper.pageEntitiesToDTOs(users);//user.map(UserDTO::new);
    }
    
}
