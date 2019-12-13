package com.bilichenko.geoipserver.controller;

import com.bilichenko.geoipserver.controller.exceptions.Ip2LocationNotFoundException;
import com.bilichenko.geoipserver.dto.Ip2LocationDto;
import com.bilichenko.geoipserver.model.Ip2Location;
import com.bilichenko.geoipserver.service.Ip2LocationService;
import com.bilichenko.geoipserver.utils.IpConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoIpController {

    @Autowired
    private Ip2LocationService ip2LocationService;
    @Autowired
    private IpConverter ipConverter;

    @RequestMapping(value = "/geoip/{ip}", method = RequestMethod.GET, produces = "application/JSON")
    public Ip2LocationDto getLocationFromIp(@PathVariable("ip") String ipv4) {
        Long decimalIp = ipConverter.convertIpv4ToLong(ipv4);
        Ip2Location entity = ip2LocationService.getByDecimalIp(decimalIp)
                .orElseThrow(() -> new Ip2LocationNotFoundException("no location found for ip", ipv4));

        Ip2LocationDto dto = Ip2LocationDto.of(entity);
        dto.setCanonicalIPv4Representation(ipv4);
        dto.setIpv4(decimalIp);
        return dto;
    }
}
