package com.bilichenko.geoipserver.controller;

import com.bilichenko.geoipserver.dto.Ip2LocationDto;
import com.bilichenko.geoipserver.model.Ip2Location;
import com.bilichenko.geoipserver.service.Ip2LocationService;
import com.bilichenko.geoipserver.translator.Ip2LocationTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoIpController {

    private Ip2LocationService ip2LocationService;
    private Ip2LocationTranslator ip2LocationTranslator;

    @Autowired
    public GeoIpController(Ip2LocationService ip2LocationService, Ip2LocationTranslator ip2LocationTranslator) {
        this.ip2LocationService = ip2LocationService;
        this.ip2LocationTranslator = ip2LocationTranslator;
    }

    @RequestMapping(value = "/geoip/{ip}", method = RequestMethod.GET, produces = "application/JSON")
    public Ip2LocationDto getLocationFromIp(@PathVariable("ip") String ipv4) {
        Ip2Location entity = ip2LocationService.getByIp(ipv4);
        return ip2LocationTranslator.toDto(entity);
    }
}
