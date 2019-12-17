package com.bilichenko.geoipserver.service;

import com.bilichenko.geoipserver.controller.exceptions.Ip2LocationNotFoundException;
import com.bilichenko.geoipserver.dao.Ip2LocationDao;
import com.bilichenko.geoipserver.model.Ip2Location;
import com.bilichenko.geoipserver.utils.IpConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Ip2LocationService {

    private Ip2LocationDao ip2LocationDao;

    @Autowired
    public Ip2LocationService(Ip2LocationDao ip2LocationDao) {
        this.ip2LocationDao = ip2LocationDao;
    }

    public Ip2Location getByIp(String ipv4) {
        Long decimalIpv4 = IpConverter.convertIpv4ToLong(ipv4);
        Ip2Location ip2Location = ip2LocationDao.getByDecimalIp(decimalIpv4)
                .orElseThrow(() -> new Ip2LocationNotFoundException("no location found for ip", ipv4));

        String canonicalIpv4 = IpConverter.toCanonicalIpv4Representation(ipv4);
        ip2Location.setCanonicalIPv4Representation(canonicalIpv4);
        ip2Location.setIpv4(decimalIpv4);
        return ip2Location;
    }
}
