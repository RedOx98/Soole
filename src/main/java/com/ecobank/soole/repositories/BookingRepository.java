package com.ecobank.soole.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecobank.soole.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // List<Booking> findAllByAccountId(Long id);
    List<Booking> findByCreatedAt(LocalDateTime createdAt);
    List<Booking> findByAccountIdAndBusBusId(Long accountId, Long busId);
}
