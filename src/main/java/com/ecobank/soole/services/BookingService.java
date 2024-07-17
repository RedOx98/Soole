package com.ecobank.soole.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecobank.soole.models.Booking;
import com.ecobank.soole.models.Bus;
import com.ecobank.soole.repositories.BookingRepository;
import com.ecobank.soole.repositories.BusRepository;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository busRepository;

    public Booking save(Booking booking, Long busId){
        if (booking.getId() == null) {
            booking.setCreatedAt(LocalDateTime.now());
        }
        if (booking.getCreatedAt() == null) {
            booking.setCreatedAt(LocalDateTime.now());
        }

        Optional<Bus> optionalBus = busRepository.findById(busId);
        if (optionalBus.isPresent()) {
            Bus bus = optionalBus.get();
            booking.setBus(bus);
        }else{
            throw new RuntimeException();
        }

        return bookingRepository.save(booking);
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public List<Booking> findByDate(LocalDateTime date){
        return bookingRepository.findByCreatedAt(date);
    }
}
