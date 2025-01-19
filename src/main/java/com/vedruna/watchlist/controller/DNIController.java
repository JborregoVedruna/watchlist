package com.vedruna.watchlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedruna.watchlist.dto.ResponseDTO;
import com.vedruna.watchlist.dto.entitiesdtos.DNICardDTO;
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
    public ResponseEntity<ResponseDTO> postDNI(@RequestBody DNICardDTO document) {
        boolean b = dniService.insertDNI(document);
        if (b) {
            return ResponseEntity.ok(new ResponseDTO("DNI Insertado correctamente"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("No se ha podido insertar el DNI"));
        }
    }

    @DeleteMapping("/{dniCardId}")
    public ResponseEntity<ResponseDTO> deleteDNI(@PathVariable Integer dniCardId) {
        boolean b = dniService.deleteDNI(dniCardId);
        if (b) {
            return ResponseEntity.ok(new ResponseDTO("DNI Eliminado correctamente"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("No se ha podido eliminar el DNI"));
        }
    }
    
}
