package com.app.flightstats.service;

import com.app.flightstats.model.FlightInformation;
import com.app.flightstats.model.ScheduledFlights;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class FlightStatServiceImpl implements FlightStatService {

    @Autowired
    private Environment env;


    public FlightStatServiceImpl() {
    }

    @Override
    public String flightByRouteAndGivenDate(FlightInformation flightInformation) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> entityResponse = restTemplate.getForEntity(getFlightByRouteAndGivenDateUrl(flightInformation), String.class);
            flightInformation.setScheduledFlights(scheduledFlightNodeToScheduledFlight(entityResponse));
            ObjectMapper result = new ObjectMapper();
            return result.writeValueAsString(flightInformation);

        } catch (Exception e) {
            return "An error occurred by: " + e.getMessage();
        }
    }

    private List<ScheduledFlights> scheduledFlightNodeToScheduledFlight(ResponseEntity<String> entityResponse) throws Exception {

        List<ScheduledFlights> flights = new ArrayList();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(entityResponse.getBody());
        JsonNode scheduledFlightsNode = rootNode.path("scheduledFlights");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
        Iterator<JsonNode> elements = scheduledFlightsNode.elements();

        while (elements.hasNext()) {
            JsonNode element = elements.next();
            Date parsedDepartureTime = dateFormat.parse(element.get("departureTime").asText().replace("T", " "));
            Date parsedArrivalTime = dateFormat.parse(element.get("arrivalTime").asText().replace("T", " "));

            ScheduledFlights scheduledFlights =
                    new ScheduledFlights(element.get("carrierFsCode").asText(), element.get("flightNumber").asInt(), element.get("stops").asText(), element.get("departureAirportFsCode").asText(),
                            element.get("arrivalAirportFsCode").asText(), new Timestamp(parsedDepartureTime.getTime()), new Timestamp(parsedArrivalTime.getTime()));

            flights.add(scheduledFlights);
        }

        return flights;
    }

    private String getFlightByRouteAndGivenDateUrl(FlightInformation flightInformation) {

        StringBuilder url = new StringBuilder();

        url.append(env.getProperty("flightstats.uri.host"));
        url.append(env.getProperty("flightstats.uri.from"));
        url.append(flightInformation.get_departureAirportCode());
        url.append(env.getProperty("flightstats.uri.to"));
        url.append(flightInformation.get_arrivalAirportCode());
        url.append(env.getProperty("flightstats.uri.arriving"));
        url.append(flightInformation.get_year());
        url.append("/");
        url.append(flightInformation.get_month());
        url.append("/");
        url.append(flightInformation.get_day());
        url.append(env.getProperty("flightstats.uri.appId"));
        url.append(env.getProperty("flightstats.appId"));
        url.append(env.getProperty("flightstats.uri.appKey"));
        url.append(env.getProperty("flightstats.appKey"));

        return url.toString();
    }
}
