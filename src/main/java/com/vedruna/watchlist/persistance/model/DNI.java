package com.vedruna.watchlist.persistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dnis")
public class DNI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dni_id", nullable = false)
    Integer dniId;

    @Column(name = "number", nullable = false, unique = true)
    @Size(min = 9, max = 9, message = "DNI number must be 9 characters long")
    String number;

    @Column(name = "front_img", nullable = true)
    String frontImg;

    @Column(name = "back_img", nullable = true)
    String backImg;

    @OneToOne
    @JoinColumn(name="Users_user_id", referencedColumnName = "user_id")
    User dniOwner;
}
