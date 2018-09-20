package com.app.flightstats.demo;

import com.app.flightstats.model.Airport;
import com.app.flightstats.repository.AirportRepository;
import com.app.flightstats.service.AirportService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {

    @Autowired
    private AirportService _airportService;

    @Autowired
    private AirportRepository _aiportRepository;

    private Airport airport;

    @Before
    public void setupData() {
        airport = new Airport("T_Airport", "T_City",
                "T_Country", new Timestamp(new Date().getTime()));
        _aiportRepository.save(airport);
    }

    @After
    public void cleanSetupData() {
        _aiportRepository.delete(airport);
    }

    @Test
    public void findAirportByName() {
        Assert.assertEquals("T_Airport", _airportService.findByName("T_Airport"));
    }

    @Test
    public void findNonExistentAirport() {
        Assert.assertEquals("Airport not found", _airportService.findByName("INVALID_AIRPORT_NAME"));
    }

}
