package com.bilichenko.geoipserver.utils;

import com.bilichenko.geoipserver.utils.exceptions.NotValidIpv4Exception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IpConverterTest {

    @Test
    public void convertIpv4ToLong_canonicalRepresentation() {
        String ip1 = "1.1.1.1";
        String ip2 = "0.1.2.3";
        String ip3 = "192.168.10.1";
        String ip4 = "192.168.19.1";

        assertEquals(16843009L, IpConverter.convertIpv4ToLong(ip1));
        assertEquals(66051L, IpConverter.convertIpv4ToLong(ip2));
        assertEquals(3232238081L, IpConverter.convertIpv4ToLong(ip3));
        assertEquals(3232240385L, IpConverter.convertIpv4ToLong(ip4));
    }

    @Test
    public void convertIpv4ToLong_notCanonicalRepresentation() {
        String ip1 = "192.168.321";
        String ip2 = "1.2";
        String ip3 = "123456";

        assertEquals(3232235841L, IpConverter.convertIpv4ToLong(ip1));
        assertEquals(16777218L, IpConverter.convertIpv4ToLong(ip2));
        assertEquals(123456L, IpConverter.convertIpv4ToLong(ip3));
    }

    @Test
    public void convertIpv4ToLong_notValidInput_throwsException() {
        String notValidIp1 = "192.168.10.300";
        String notValidIp2 = "22..22";
        String notValidIp3 = "9876.123456.2";

        assertThrows(NotValidIpv4Exception.class, () -> IpConverter.convertIpv4ToLong(notValidIp1));
        assertThrows(NotValidIpv4Exception.class, () -> IpConverter.convertIpv4ToLong(notValidIp2));
        assertThrows(NotValidIpv4Exception.class, () -> IpConverter.convertIpv4ToLong(notValidIp3));
    }

    @Test
    public void toCanonicalIpv4Representation_notCanonicalRepresentation() {
        String ip1 = "123.12.45678";
        String ip2 = "1.100";
        String ip3 = "3232238081";

        assertEquals("123.12.178.110", IpConverter.toCanonicalIpv4Representation(ip1));
        assertEquals("1.0.0.100", IpConverter.toCanonicalIpv4Representation(ip2));
        assertEquals("192.168.10.1", IpConverter.toCanonicalIpv4Representation(ip3));
    }

    @Test
    public void toCanonicalIpv4Representation_CanonicalRepresentation() {
        String ip1 = "1.1.1.1";
        String ip2 = "0.1.2.3";
        String ip3 = "192.168.10.1";

        assertEquals(ip1, IpConverter.toCanonicalIpv4Representation(ip1));
        assertEquals(ip2, IpConverter.toCanonicalIpv4Representation(ip2));
        assertEquals(ip3, IpConverter.toCanonicalIpv4Representation(ip3));
    }

    @Test
    public void toCanonicalIpv4Representation_notValidInput_throwsException() {
        String notValidIp1 = "123232.123.1";
        String notValidIp2 = "23452.12345.54321";
        String notValidIp3 = "12.2.21.12.2";

        assertThrows(NotValidIpv4Exception.class, () -> IpConverter.toCanonicalIpv4Representation(notValidIp1));
        assertThrows(NotValidIpv4Exception.class, () -> IpConverter.toCanonicalIpv4Representation(notValidIp2));
        assertThrows(NotValidIpv4Exception.class, () -> IpConverter.toCanonicalIpv4Representation(notValidIp3));
    }
}
