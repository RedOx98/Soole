package com.ecobank.soole.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecobank.soole.models.Booking;
import com.ecobank.soole.repositories.BookingRepository;
import com.ecobank.soole.util.constants.BookingTimeFrameUtil;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking save(Booking booking) {
        if (booking.getId() == null) {
            booking.setCreatedAt(LocalDateTime.now());
            booking.setTake_off_point("EPAC");
            booking.setTime_of_departure(LocalDateTime.now());
        }
        if (booking.getCreatedAt() == null) {
            booking.setCreatedAt(LocalDateTime.now());
        }
        return bookingRepository.save(booking);

    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findByDate(LocalDateTime date) {
        return bookingRepository.findByCreatedAt(date);
    }

    // public List<Booking> findBusBookings(Long id){
    // return bookingRepository.findByBus_Id(id);
    // }

    // public List<Booking> findBookingsByBusId(Long id) {
    //     return bookingRepository.findAllByBus_Id(id);
    // }

    // public List<Booking> findBookingsByAccountId(Long id) {
    //     return bookingRepository.findAllByAccount_Id(id);
    // }

    public Boolean hasExistingBooking(Long userId, Long busId, LocalDateTime dateTime){
        boolean isMorning = BookingTimeFrameUtil.isMorning(dateTime);
        boolean isEvening = BookingTimeFrameUtil.isEvening(dateTime);

        List<Booking> existingBookings = bookingRepository.findByAccountIdAndBusBusId(userId, busId);
        for(Booking booking: existingBookings){
            if (isEvening&& BookingTimeFrameUtil.isEvening(booking.getCreatedAt())) {
                return true;
            }
            if (isMorning&& BookingTimeFrameUtil.isMorning(booking.getCreatedAt())) {
                return true;
            }
        }
        return false;
    }
}
