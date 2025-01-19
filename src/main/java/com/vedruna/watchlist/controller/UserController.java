package com.vedruna.watchlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.watchlist.dto.entitiesdtos.UserDTO;
import com.vedruna.watchlist.service.UserServiceI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceI userService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok().body(userService.selectAllUsers(pageable));
    }

    @GetMapping("/name")
    public ResponseEntity<Page<UserDTO>> getUserByUsernameStartingWith(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok().body(userService.selectUserByUsernameStartingWith(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        UserDTO userDTO = userService.selectUserById(id);

        if (userDTO != null) {
            return ResponseEntity.ok().body(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
