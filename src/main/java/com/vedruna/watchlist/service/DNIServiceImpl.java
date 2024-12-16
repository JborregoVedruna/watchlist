package com.vedruna.watchlist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.dto.DNIDTO;
import com.vedruna.watchlist.persistance.model.DNI;
import com.vedruna.watchlist.persistance.model.User;
import com.vedruna.watchlist.persistance.repository.DNIRepositoryI;
import com.vedruna.watchlist.persistance.repository.UserRepositoryI;

@Service
public class DNIServiceImpl implements DNIServiceI {

    @Autowired
    private DNIRepositoryI dniRepositoryI;

    @Autowired
    private UserRepositoryI userRepositoryI;

    @Override
    public String insertDNI(DNIDTO document) {
        Optional<User> user = userRepositoryI.findById(document.getDniOwnerId());

        if (user.isPresent()) {
            DNI dni = new DNI();
            dni.setDniOwner(user.get());
            dni.setNumber(document.getNumber());
            dni.setFrontImg(document.getFrontImg());
            dni.setBackImg(document.getBackImg());
            dniRepositoryI.save(dni);
            return "DNI Insertado correctamente";
        } else {
            return "El usuario con dicho id no existe";
        }
    }
    
}
