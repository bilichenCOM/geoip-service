package com.bilichenko.geoipserver.service;

import com.bilichenko.geoipserver.dao.Ip2LocationDao;
import com.bilichenko.geoipserver.model.Ip2Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class Ip2LocationServiceTest {

    @Autowired
    private Ip2LocationService ip2LocationService;
    @MockBean
    private Ip2LocationDao ip2LocationDao;

    @Test
    public void getByIpTest() {
        Long testIp = 16843009L;

        Ip2Location mockEntity = new Ip2Location();
        mockEntity.setIpFrom(16843008L);
        mockEntity.setIpTo(16843010L);
        mockEntity.setCountryCode("UA");
        mockEntity.setRegionName("Kyiv region");
        mockEntity.setCityName("Kyiv");
        mockEntity.setLatitude(123.456);
        mockEntity.setLongitude(98.765);

        when(ip2LocationDao.getByDecimalIp(testIp)).thenReturn(Optional.of(mockEntity));

        Assertions.assertEquals(Optional.of(mockEntity), ip2LocationService.getByDecimalIp(testIp));
    }
}
