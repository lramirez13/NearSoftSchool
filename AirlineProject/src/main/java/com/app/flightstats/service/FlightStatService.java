package com.app.flightstats.service;

import com.app.flightstats.model.FlightInformation;
import com.app.flightstats.model.ScheduledFlights;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightStatService {

    String flightByRouteAndGivenDate(FlightInformation flightInformation);

    List<ScheduledFlights> scheduledFlightNodeToScheduledFlight(String flightNode) throws Exception ;

}
