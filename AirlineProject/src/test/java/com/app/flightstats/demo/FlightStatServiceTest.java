package com.app.flightstats.demo;

import com.app.flightstats.model.FlightInformation;
import com.app.flightstats.model.ScheduledFlights;
import com.app.flightstats.service.FlightStatService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightStatServiceTest {

    @Autowired
    FlightStatService _flightStatService;

    private String jsonExample;

    @Before
    public void setupData() throws Exception {
        URL path = ClassLoader.getSystemResource("FlightStatServiceTest");

        byte[] jsonData = Files.readAllBytes(Paths.get(path.toURI()));

        jsonExample = new String(jsonData);
    }

    @Test
    public void findFlightByRouteAndGivenDate() throws Exception{

        List<ScheduledFlights> scheduledFlights = _flightStatService.scheduledFlightNodeToScheduledFlight(jsonExample);
        Assert.assertEquals("","");
    }

}
