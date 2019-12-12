package com.bilichenko.geoipserver.dao;

import com.bilichenko.geoipserver.model.Ip2Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Ip2LocationDao extends JpaRepository<Ip2Location, Long> {
    Optional<Ip2Location> getByIpFromLessThanAndIpToGreaterThan(Long decimalIp);
}
