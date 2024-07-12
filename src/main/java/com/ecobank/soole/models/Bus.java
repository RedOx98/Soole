package com.ecobank.soole.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String plateNumber;

    private String route;

    private String captain;

    private String rep;

    private String driver;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
