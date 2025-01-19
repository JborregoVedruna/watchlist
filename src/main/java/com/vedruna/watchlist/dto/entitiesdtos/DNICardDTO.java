package com.vedruna.watchlist.dto.entitiesdtos;

import com.vedruna.watchlist.persistance.model.DNICard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DNICardDTO {
    Integer dniId;
    String number;
    String frontImg;
    String backImg;
    Integer dniOwnerId;

    // public DNICardDTO(DNICard dni) {
    //     this.dniId = dni.getDniId();
    //     this.number = dni.getNumber();
    //     this.frontImg = dni.getFrontImg();
    //     this.backImg = dni.getBackImg();
    //     this.dniOwnerId = dni.getDniOwner().getUserId();
    // }
}
