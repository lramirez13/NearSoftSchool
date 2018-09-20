package com.app.flightstats.service;

import com.app.flightstats.model.FlightInformation;
import org.springframework.stereotype.Service;

@Service
public interface FlightStatService {

    String flightByRouteAndGivenDate(FlightInformation flightInformation);

}
