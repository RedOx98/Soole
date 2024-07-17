package com.ecobank.soole.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ecobank.soole.models.Waitlist;

public interface WaitlistRepository extends JpaRepository<Waitlist, Long> {
    ;
}
