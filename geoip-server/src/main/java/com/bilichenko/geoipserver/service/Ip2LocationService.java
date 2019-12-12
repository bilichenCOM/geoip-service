package com.bilichenko.geoipserver.service;

import com.bilichenko.geoipserver.model.Ip2Location;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Ip2LocationService {

    public Optional<Ip2Location> get(String ipv4) {
        return Optional.empty();
    }
}
