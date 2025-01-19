package com.vedruna.watchlist.dto.entitiesdtos;

import com.vedruna.watchlist.persistance.model.DNICard;
import com.vedruna.watchlist.persistance.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    Integer userId;
    String username;
    String email;
    String rolName;
    DNICardDTO document;

    // public UserDTO(User user) {
    //     this.userId = user.getUserId();
    //     this.username = user.getUsername();
    //     this.email = user.getEmail();
    //     this.rolName = user.getUserRol().getRolName();
    //     this.document = checkDni(user.getDocument());
    // }


    // private DNICardDTO checkDni(DNICard document) {
    //     if (document != null) {
    //         return new DNICardDTO(document);
    //     } else {
    //         return null;
    //     }
    // }

}
