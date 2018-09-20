package com.app.flightstats.repository;



import com.app.flightstats.model.Airline;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AirlineRepository extends CrudRepository<Airline, Long> {

    Optional<Airline> findByName(String name);

    Optional<Airline> findByCode(String code);


}
