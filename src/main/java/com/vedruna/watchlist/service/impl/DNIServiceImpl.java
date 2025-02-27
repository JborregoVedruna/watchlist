package com.vedruna.watchlist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.dto.entitydto.DNICardDTO;
import com.vedruna.watchlist.exception.UserNotFoundException;
import com.vedruna.watchlist.persistance.model.DNICard;
import com.vedruna.watchlist.persistance.model.User;
import com.vedruna.watchlist.persistance.repository.DNIRepositoryI;
import com.vedruna.watchlist.persistance.repository.UserRepositoryI;
import com.vedruna.watchlist.service.DNIServiceI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DNIServiceImpl implements DNIServiceI {

    @Autowired
    private DNIRepositoryI dniRepositoryI;

    @Autowired
    private UserRepositoryI userRepositoryI;

    @Override
    public DNICard insertDNI(DNICardDTO document) {
        log.debug("Inserting DNI: {}", document);
        User user = userRepositoryI.findById(document.getDniOwnerId())
                        .orElseThrow(() -> new UserNotFoundException());
        log.debug("User: {}", user);
        DNICard dni = new DNICard();
        dni.setDniOwner(user);
        dni.setNumber(document.getNumber());
        dni.setFrontImg(document.getFrontImg());
        dni.setBackImg(document.getBackImg());
        log.debug("DNI to save: {}", dni);
        DNICard savedDNI = dniRepositoryI.save(dni);
        log.debug("Saved DNI: {}", savedDNI);
        return savedDNI;
    }

    @Override
    public void deleteDNI(Integer dniCardId) {
        log.debug("Deleting DNI with id: {}", dniCardId);
        dniRepositoryI.deleteById(dniCardId);
    }
    
}
