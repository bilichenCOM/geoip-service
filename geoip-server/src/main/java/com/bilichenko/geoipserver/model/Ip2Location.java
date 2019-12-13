package com.bilichenko.geoipserver.model;

import lombok.Data;

@Data
public class Ip2Location {

    private Long ipFrom;
    private Long ipTo;
    private String countryCode;
    private String countryName;
    private String regionName;
    private String cityName;
    private Double latitude;
    private Double longitude;
}
