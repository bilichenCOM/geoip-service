package com.bilichenko.geoipserver.service;

import com.bilichenko.geoipserver.dao.Ip2LocationDao;
import com.bilichenko.geoipserver.model.Ip2Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Ip2LocationService {

    @Autowired
    private Ip2LocationDao ip2LocationDao;

    public Optional<Ip2Location> getByDecimalIp(Long decimalIp) {
        return ip2LocationDao.getByDecimalIp(decimalIp);
    }
}
