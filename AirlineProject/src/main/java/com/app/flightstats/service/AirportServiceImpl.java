package com.app.flightstats.service;


import com.app.flightstats.model.Airport;
import com.app.flightstats.repository.AirportRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository _airportRepository;

    @Autowired
    private Environment env;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository) {
        _airportRepository = airportRepository;
    }

    @Override
    public String findByName(String name) {
        Optional<Airport> airportByName = _airportRepository.findByName(name);
        if (airportByName.isPresent()) {
            return airportByName.get().getName();
        }
        return "Airport not found";
    }

    @Override
    public String findAll() {
        final StringWriter sw = new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(sw, _airportRepository.findAll());
        } catch (Exception e) {

        }
        return sw.toString();
    }

    @Override
    public void populateAllAirportTable() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> entityResponse = restTemplate.getForEntity(getAirportUrl(), String.class);
        try {
            JsonNode rootNode = objectMapper.readTree(entityResponse.getBody());
            JsonNode airports = rootNode.path("airports");
            Iterator<JsonNode> elements = airports.elements();
            while (elements.hasNext()) {
                JsonNode element = elements.next();
                Airport airport = new Airport(element.get("fs").asText(), element.get("city").asText(),
                        element.get("countryName").asText(), new Timestamp(new Date().getTime()));
                _airportRepository.save(airport);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getAirportUrl() {

        StringBuilder url = new StringBuilder();

        url.append(env.getProperty("flightstats.uri.host"));
        url.append(env.getProperty("flighstats.aiports"));
        url.append(env.getProperty("flightstats.uri.appId"));
        url.append(env.getProperty("flightstats.appId"));
        url.append(env.getProperty("flightstats.uri.appKey"));
        url.append(env.getProperty("flightstats.appKey"));

        return url.toString();
    }


}