package com.app.flightstats.repository;


import com.app.flightstats.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {

    Optional<Airport> findByName(String name);

    List<Airport> findAll();

}
