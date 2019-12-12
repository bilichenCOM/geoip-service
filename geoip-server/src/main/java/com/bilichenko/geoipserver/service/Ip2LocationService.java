package com.bilichenko.geoipserver.service;

import com.bilichenko.geoipserver.model.Ip2Location;
import com.bilichenko.geoipserver.utils.IpConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Ip2LocationService {

//    @Autowired
//    private Ip2LocationDao ip2LocationDao;
    @Autowired
    private IpConverter ipConverter;

    public Optional<Ip2Location> get(String ipv4) {
        Long decimalIp = ipConverter.convertIpv4ToLong(ipv4);
//        return ip2LocationDao.getByIpFromLessThanAndIpToGreaterThan(decimalIp);
        return Optional.empty();
    }
}
