package com.bilichenko.geoipserver.translator;

import com.bilichenko.geoipserver.dto.Ip2LocationDto;
import com.bilichenko.geoipserver.model.Ip2Location;
import org.springframework.stereotype.Component;

@Component
public class Ip2LocationTranslator implements Translator<Ip2Location, Ip2LocationDto> {

    @Override
    public Ip2LocationDto toDto(Ip2Location entity) {
        Ip2LocationDto dto = new Ip2LocationDto();
        dto.setCanonicalIPv4Representation(entity.getCanonicalIPv4Representation());
        dto.setCityName(entity.getCityName());
        dto.setCountryCode(entity.getCountryCode());
        dto.setCountryName(entity.getCountryName());
        dto.setIpv4(entity.getIpv4());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setRegionName(entity.getRegionName());
        return dto;
    }
}
