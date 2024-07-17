package com.ecobank.soole.services;

import org.springframework.data.domain.Page;

import com.ecobank.soole.models.Bus;
import com.ecobank.soole.payload.bus.BusFetchRequestDTO;
import com.ecobank.soole.payload.bus.CreateBusDTO;
import com.ecobank.soole.payload.bus.CreateRouteDTO;

public interface BusService {
    void createBus(CreateBusDTO createBusDTO);

    void addRouteDetails(CreateRouteDTO createRouteDTO, String busId);

    Page<Bus> fetchBuses(BusFetchRequestDTO requestDTO);

    void deleteBus(String busId);

    void updateBusDetails(CreateBusDTO createBusDTO, String busId);
}
