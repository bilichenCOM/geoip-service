package com.bilichenko.geoipserver.dao;

import com.bilichenko.geoipserver.model.Ip2Location;

import java.util.Optional;

public interface Ip2LocationDao {

    Optional<Ip2Location> getByDecimalIp(Long decimalIp);
}
