package com.ecobank.soole.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private LocalDateTime time_of_departure;

    private LocalDateTime createdAt;

    private String take_off_point;

    private String drop_off_point;

    private String route;

    

    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "bus_id", nullable = true)
    private Bus bus;
}
