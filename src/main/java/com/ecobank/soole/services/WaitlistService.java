package com.ecobank.soole.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecobank.soole.repositories.WaitlistRepository;

@Service
public class WaitlistService {
    
    @Autowired
    private WaitlistRepository waitlistRepository;
}
