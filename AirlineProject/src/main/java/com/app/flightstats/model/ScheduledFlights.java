package com.app.flightstats.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.sql.Timestamp;

public class ScheduledFlights {

    private String carrierFsCode;

    private int flightNumber;

    private String stops;

    private String departureTerminal;

    private String arrivalTerminal;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Timestamp departureTime;

    @JsonSerialize(using = JsonDateSerializer.class)
    private Timestamp arrivalTime;

    public ScheduledFlights(String carrierFsCode, int flightNumber, String stops, String departureTerminal, String arrivalTerminal, Timestamp departureTime, Timestamp arrivalTime) {
        this.carrierFsCode = carrierFsCode;
        this.flightNumber = flightNumber;
        this.stops = stops;
        this.departureTerminal = departureTerminal;
        this.arrivalTerminal = arrivalTerminal;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getCarrierFsCode() {
        return carrierFsCode;
    }

    public void setCarrierFsCode(String carrierFsCode) {
        this.carrierFsCode = carrierFsCode;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    public void setArrivalTerminal(String arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
