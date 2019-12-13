package com.bilichenko.geoipserver.dto;

import com.bilichenko.geoipserver.model.Ip2Location;
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

    public static Ip2LocationDto of(Ip2Location entity) {
        Ip2LocationDto dto = new Ip2LocationDto();
        dto.setCityName(entity.getCityName());
        dto.setCountryName(entity.getCountryName());
        dto.setRegionName(entity.getRegionName());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        return dto;
    }
}
