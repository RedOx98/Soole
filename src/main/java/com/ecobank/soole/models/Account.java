package com.ecobank.soole.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// @Table(name = "account")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password_hash;

    private String authorities;

    private LocalDateTime createdAt;

    private String level;

    private String telephone;

    private String firstName;

    private String lastName;

    private String username;

    private String verified;

    private String route;

    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "id", nullable = true)
    private Bus bus;

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
