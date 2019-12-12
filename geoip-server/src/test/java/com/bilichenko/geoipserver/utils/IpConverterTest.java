package com.bilichenko.geoipserver.utils;

import com.bilichenko.geoipserver.utils.exceptions.NotValidIpv4Exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IpConverterTest {

    private static final IpConverter ipConverter = new IpConverter();

    @Test
    public void convertIpv4ToIntTest() {
        String ip1 = "1.1.1.1";
        String ip2 = "0.1.2.3";
        String ip3 = "192.168.10.1";
        String ip4 = "256.192.122.333";

        assertEquals(16843009L, ipConverter.convertIpv4ToLong(ip1));
        assertEquals(66051L, ipConverter.convertIpv4ToLong(ip2));
        assertEquals(3232238081L, ipConverter.convertIpv4ToLong(ip3));
        assertThrows(NotValidIpv4Exception.class, () -> ipConverter.convertIpv4ToLong(ip4));
    }
}
