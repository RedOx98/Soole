package com.ecobank.soole.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecobank.soole.models.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    // List<Bus> findByAccount_id(Long id);
}
