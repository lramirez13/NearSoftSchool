package com.app.flightstats.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightInformation {

    private String _departureAirportCode;

    private String _arrivalAirportCode;

    private int _year;

    private int _month;

    private int _day;

    public FlightInformation(String departureAirportCode, String arrivalAirportCode, int year, int month, int day) {
        _departureAirportCode = departureAirportCode;
        _arrivalAirportCode = arrivalAirportCode;
        _year = year;
        _month = month;
        _day = day;
    }

    public FlightInformation(){
    };

    private List<ScheduledFlights> scheduledFlights;

    public String get_departureAirportCode() {
        return _departureAirportCode;
    }

    public void set_departureAirportCode(String _departureAirportCode) {
        this._departureAirportCode = _departureAirportCode;
    }

    public String get_arrivalAirportCode() {
        return _arrivalAirportCode;
    }

    public void set_arrivalAirportCode(String _arrivalAirportCode) {
        this._arrivalAirportCode = _arrivalAirportCode;
    }

    public int get_year() {
        return _year;
    }

    public void set_year(int _year) {
        this._year = _year;
    }

    public int get_month() {
        return _month;
    }

    public void set_month(int _month) {
        this._month = _month;
    }

    public int get_day() {
        return _day;
    }

    public void set_day(int _day) {
        this._day = _day;
    }

    public List<ScheduledFlights> getScheduledFlights() {
        return scheduledFlights;
    }

    public void setScheduledFlights(List<ScheduledFlights> scheduledFlights) {
        this.scheduledFlights = scheduledFlights;
    }

    @Override
    public String toString() {
        return "FlightInformation{" +
                "_departureAirportCode='" + _departureAirportCode + '\'' +
                ", _arrivalAirportCode='" + _arrivalAirportCode + '\'' +
                ", _year=" + _year +
                ", _month=" + _month +
                ", _day=" + _day +
                ", scheduledFlights=" + scheduledFlights +
                '}';
    }
}
