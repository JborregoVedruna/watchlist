package com.vedruna.watchlist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedruna.watchlist.dto.entitiesdtos.DNICardDTO;
import com.vedruna.watchlist.persistance.model.DNICard;
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
    public boolean insertDNI(DNICardDTO document) {
        Optional<User> user = userRepositoryI.findById(document.getDniOwnerId());

        if (user.isPresent()) {
            DNICard dni = new DNICard();
            dni.setDniOwner(user.get());
            dni.setNumber(document.getNumber());
            dni.setFrontImg(document.getFrontImg());
            dni.setBackImg(document.getBackImg());
            try {
                dniRepositoryI.save(dni);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteDNI(Integer dniCardId) {
        try {
            dniRepositoryI.deleteById(dniCardId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
}
