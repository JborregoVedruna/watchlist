package com.vedruna.watchlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.watchlist.dto.DNIDTO;
import com.vedruna.watchlist.dto.ResponseDTO;
import com.vedruna.watchlist.service.DNIServiceI;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/DNI")
@CrossOrigin
public class DNIController {

    @Autowired
    private DNIServiceI dniService;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> postDNI(@RequestBody DNIDTO document) {
        String message = dniService.insertDNI(document);
        if (message.equals("DNI Insertado correctamente")) {
            return ResponseEntity.ok(new ResponseDTO(message));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO(message));
        }
    }
    
}
