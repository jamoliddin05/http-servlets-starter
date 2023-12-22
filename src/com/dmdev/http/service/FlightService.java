package com.dmdev.http.service;

import com.dmdev.http.dao.FlightDao;
import com.dmdev.http.dto.FlightDto;

import java.util.List;

public class FlightService {
    private final FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll() {

    }
}
