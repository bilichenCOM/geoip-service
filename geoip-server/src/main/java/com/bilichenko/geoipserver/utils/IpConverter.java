package com.bilichenko.geoipserver.utils;

import com.bilichenko.geoipserver.utils.exceptions.NotValidIpv4Exception;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class IpConverter {

    public long convertIpv4ToLong(String ipv4) {
        checkIsIpCorrect(ipv4);
        String[] blocks = ipv4.split("\\.");
        String binaryRepresentation = Arrays.stream(blocks)
                .map(Integer::parseInt)
                .map(Integer::toBinaryString)
                .map(IpConverter::addLeadingZeroes)
                .collect(Collectors.joining());
        return Long.parseUnsignedLong(binaryRepresentation, 2);
    }

    private static String addLeadingZeroes(String s) {
        if (s.length() >= 8){
            return s;
        }
        int zeroesNeeded = 8 - s.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i++ < zeroesNeeded;) {
            result.append(0);
        }
        result.append(s);
        return result.toString();
    }

    private static void checkIsIpCorrect(String ipv4) {
        Pattern ipv4Pattern = Pattern
                .compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher matcher = ipv4Pattern.matcher(ipv4);
        if (!matcher.matches()) {
            throw new NotValidIpv4Exception(ipv4);
        }
    }
}
