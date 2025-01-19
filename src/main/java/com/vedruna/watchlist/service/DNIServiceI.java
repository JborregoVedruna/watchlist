package com.vedruna.watchlist.service;

import com.vedruna.watchlist.dto.entitiesdtos.DNICardDTO;

public interface DNIServiceI {
    boolean insertDNI(DNICardDTO document);

    boolean deleteDNI(Integer dniCardId);
}
