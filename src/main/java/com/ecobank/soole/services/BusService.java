package com.ecobank.soole.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecobank.soole.models.Bus;
import com.ecobank.soole.repositories.BusRepository;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public Bus save(Bus bus){
        return busRepository.save(bus);
    }

    public List<Bus> findAll(){
        return busRepository.findAll();
    }

    public Optional<Bus> findById(Long id){
        return busRepository.findById(id);
    }

    // public List<Bus> findByAccount_id(Long id){
    //     return busRepository.findByAccount_id(id);
    // }
}
