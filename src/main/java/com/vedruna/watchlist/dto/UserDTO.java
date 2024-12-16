package com.vedruna.watchlist.dto;

import com.vedruna.watchlist.persistance.model.DNI;
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
    DNIDTO document;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.rolName = user.getUserRol().getRolName();
        this.document = checkDni(user.getDocument());
    }


    private DNIDTO checkDni(DNI document) {
        if (document != null) {
            return new DNIDTO(document);
        } else {
            return null;
        }
    }

}
