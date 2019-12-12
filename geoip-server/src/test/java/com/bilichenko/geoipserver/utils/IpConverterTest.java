package com.bilichenko.geoipserver.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IpConverterTest {

    @Autowired
    private IpConverter ipConverter;

    @Test
    public void convertIpv4ToIntTest() {
        String ip1 = "1.1.1.1";
        String ip2 = "0.1.2.3";
        String ip3 = "192.168.10.1";

        assertEquals(16843009L, ipConverter.convertIpv4ToInt(ip1));
        assertEquals(66051L, ipConverter.convertIpv4ToInt(ip2));
        assertEquals(3232238081L, ipConverter.convertIpv4ToInt(ip3));
    }
}
