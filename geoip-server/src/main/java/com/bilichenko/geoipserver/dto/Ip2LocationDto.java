package com.bilichenko.geoipserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Ip2LocationDto {

    private String canonicalIPv4Representation;
    private String cityName;
    private String countryCode;
    private String countryName;
    @JsonProperty("IPv4")
    private Long ipv4;
    private Double latitude;
    private Double longitude;
    private String regionName;
}
