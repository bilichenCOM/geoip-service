package com.bilichenko.geoipserver.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Ip2Location {
    @Column(name = "ip_from")
    private Long ipFrom;
    @Column(name = "ip_to")
    private Long ipTo;
    @Column(name = "county_code")
    private String countryCode;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "city_name")
    private String cityName;
    private Double latitude;
    private Double longitude;
}
