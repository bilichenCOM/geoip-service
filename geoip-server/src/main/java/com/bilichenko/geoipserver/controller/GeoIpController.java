package com.bilichenko.geoipserver.controller;

import com.bilichenko.geoipserver.controller.exceptions.Ip2LocationNotFoundException;
import com.bilichenko.geoipserver.model.Ip2Location;
import com.bilichenko.geoipserver.service.Ip2LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoIpController {

    @Autowired
    private Ip2LocationService ip2LocationService;

    @RequestMapping(value = "/geoip/{ip}", method = RequestMethod.GET, produces = "application/JSON")
    public Ip2Location getLocationFromIp(@PathVariable("ip") String ipv4) {
        return ip2LocationService.get(ipv4)
                .orElseThrow(() -> new Ip2LocationNotFoundException("no location found for ip", ipv4));
    }
}
