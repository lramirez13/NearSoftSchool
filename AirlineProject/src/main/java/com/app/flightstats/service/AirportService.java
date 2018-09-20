package com.app.flightstats.service;

import com.app.flightstats.model.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService {

    String findByName(String name);

    String findAll();

    void populateAllAirportTable();

}
