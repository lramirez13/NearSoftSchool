package com.app.flightstats.controller;


import com.app.flightstats.model.FlightInformation;
import com.app.flightstats.service.AirportService;
import com.app.flightstats.service.FlightStatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AirportController {

    private final AirportService _airportService;

    private final FlightStatService _flightStatService;

    @Autowired
    public AirportController(AirportService airportService, FlightStatService flightStatService) {

        _airportService = airportService;
        _flightStatService = flightStatService;

    }

    @RequestMapping(value = "/findByName")
    public String findByName(@RequestParam("name") String name) {

        return _airportService.findByName(name);
    }

    //http://localhost:8080/getFlightByRouteAndGivenDateUrl?departureAirportCode=LAX&arrivalAirportCode=SFO&year=2018&month=9&day=11


    @RequestMapping("/getFlightByRouteAndGivenDateUrl")
    public String findFlightByRouteAndGivenDateUrl(
            @RequestParam("departureAirportCode") String departureAirportCode,
            @RequestParam("arrivalAirportCode") String arrivalAirportCode,
            @RequestParam("year") int year,
            @RequestParam("month") int month,
            @RequestParam("day") int day) {

        FlightInformation flightInformation = new FlightInformation(departureAirportCode, arrivalAirportCode, year, month, day);
        return _flightStatService.flightByRouteAndGivenDate(flightInformation);

    }

    @RequestMapping("/Airports")
    public String getAirports() {
        return _airportService.findAll();
    }

    @RequestMapping("/populateAllAirportTable")

    public String populateAirTable() {
        _airportService.populateAllAirportTable();
        return "airTable";

    }


}
