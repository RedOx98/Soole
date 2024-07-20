package com.ecobank.soole.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecobank.soole.models.Account;
import com.ecobank.soole.models.Booking;
import com.ecobank.soole.models.Bus;
import com.ecobank.soole.payload.booking.BookingPayloadDTO;
import com.ecobank.soole.payload.booking.BookingViewDTO;
import com.ecobank.soole.repositories.AccountRepository;
import com.ecobank.soole.repositories.BookingRepository;
import com.ecobank.soole.repositories.BusRepository;
import com.ecobank.soole.services.AccountService;
import com.ecobank.soole.services.BookingService;
import com.ecobank.soole.services.BusService;
import com.ecobank.soole.util.constants.BookingEnum;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/booking")
@Slf4j
// @RequiredArgsConstructor
@Tag(name = "Booking Controller", description = "Controller for Account management")
public class BookingController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BusService busService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BusRepository busRepository;

    @PostMapping(value = "/bookseat", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "400", description = "token")
    @ApiResponse(responseCode = "201", description = "Account added")
    @ApiResponse(responseCode = "200", description = "success")
    @Operation(summary = "Reserve a spot in the chosen bus")
    @SecurityRequirement(name = "soole-demo-api")
    public ResponseEntity<BookingViewDTO> reserveSpot(@ModelAttribute BookingPayloadDTO bookingDTO,
            Authentication authentication) {
        Booking booking = new Booking();
        Optional<Bus> optionalBus = busRepository.findById(bookingDTO.getBusId());
        Bus bus = optionalBus.get();
        if (!optionalBus.isPresent()) {
            return new ResponseEntity<>(new BookingViewDTO(), HttpStatus.BAD_REQUEST);
        }
        Optional<Account> optionalAccount = accountRepository.findById(bookingDTO.getUserId());
        Account accountVerified = optionalAccount.get();
        Account account = optionalAccount.get();
        if (!optionalAccount.isPresent()) {
            return new ResponseEntity<>(new BookingViewDTO(), HttpStatus.BAD_REQUEST);
        }

        LocalDateTime now = LocalDateTime.now();
        boolean isSpecial = "SPECIAL".equals(account.getSpecial());
        if (bookingService.hasExistingBooking(account.getId(), bus.getBusId(), now, isSpecial)) {
            return new ResponseEntity<>(new BookingViewDTO(), HttpStatus.BAD_REQUEST);
        }
        List<Booking> allSeats = bookingService.findAll();
        int totalSeats = allSeats.size();

        if (accountVerified.getVerified().equals("PENDING") || accountVerified.getVerified().equals("REJECTED")) {
            return new ResponseEntity<>(new BookingViewDTO(), HttpStatus.BAD_REQUEST);
        }

        booking.setAccount(account);
        booking.setBooker(account.getFullName());
        booking.setCreatedAt(now);
        booking.setDrop_off_point(bookingDTO.getDrop_off_point());
        booking.setRoute(bookingDTO.getRoute());
        booking.setAccount(account);
        booking.setBus(bus);
        booking.setSpecial(account.getSpecial());
        booking.setBooker(account.getFullName());

        if (totalSeats < bus.getBusCapacity()) {
            booking.setStatus("RESERVED");
        } else {
            booking.setStatus("WAITLIST");
        }
        bookingService.save(booking);

        System.out.println(bookingDTO);
        BookingViewDTO bookingViewDTO = new BookingViewDTO();
        bookingViewDTO.setDrop_off_point(booking.getDrop_off_point());
        bookingViewDTO.setStatus(booking.getStatus());
        bookingViewDTO.setRoute(booking.getRoute());

        return new ResponseEntity<>(bookingViewDTO, HttpStatus.CREATED);

        // return ResponseEntity.ok("Seat Booked successfully");
    }
}

 // if (totalSeats < optionalBus.get().getBusCapacity()) {
        // booking.setDrop_off_point(bookingDTO.getDrop_off_point());
        // LocalDateTime now = LocalDateTime.now();
        // boolean isSpecial = "SPECIAL".equals(account.getSpecial());
        // if ((bookingService.hasExistingBooking(account.getId(),
        // optionalBus.get().getBusId(), now,
        // isSpecial) == true)) {
        // booking.setRoute(bookingDTO.getRoute());
        // System.out.println(bookingDTO.getRoute());
        // System.out.println(bookingDTO.getDrop_off_point());
        // System.out.println(bookingDTO.getBusId());
        // System.out.println(bookingDTO.getUserId());

        // System.out.println(booking.toString());
        // booking.setStatus(BookingEnum.status.RESERVED.toString());
        // }
        // } else {
        // booking.setStatus(BookingEnum.status.WAITLIST.toString());
        // }
        // bookingService.save(booking);
