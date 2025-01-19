package com.vedruna.watchlist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/main")
@CrossOrigin
public class MainController {

    @GetMapping("/test")
    public ResponseEntity<String> testMethod() {
        return ResponseEntity.ok().body("test");
    }
    

}
