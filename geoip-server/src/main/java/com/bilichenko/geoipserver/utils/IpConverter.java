package com.bilichenko.geoipserver.utils;

import com.bilichenko.geoipserver.utils.exceptions.NotValidIpv4Exception;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IpConverter {

    public static long convertIpv4ToLong(String ipv4) {
        if (isCanonicalRepresentation(ipv4)) {
            return convertCanonicalIpv4ToLong(ipv4);
        } else if (isNotCanonicalRepresentation(ipv4)) {
            return convertNotCanonicalIpv4ToLong(ipv4);
        } else {
            throw new NotValidIpv4Exception(ipv4);
        }
    }

    public static String toCanonicalIpv4Representation(String ipv4) {
        long decimalIpRepresentation = convertIpv4ToLong(ipv4);
        String binaryString = Long.toBinaryString(decimalIpRepresentation);
        binaryString = addLeadingZeroes(binaryString, 32); // ipv4 has 32 bits
        int numberLength = binaryString.length();
        int index = 0;
        StringBuilder canonicalRepresentation = new StringBuilder();
        for (int cnt = 0; cnt++ < 4;) {
            if (index > numberLength - 1) {
                canonicalRepresentation.append("0.");
                continue;
            }

            int nextIndex = index + 8;
            String block;
            if (nextIndex <= numberLength) {
                block = binaryString.substring(index, nextIndex);
            } else {
                block = binaryString.substring(index, numberLength);
            }
            canonicalRepresentation.append(Long.parseUnsignedLong(block, 2)).append(".");
            index = nextIndex;
        }
        int lastPeriodIndex = canonicalRepresentation.length() - 1;
        canonicalRepresentation.deleteCharAt(lastPeriodIndex);
        return canonicalRepresentation.toString();
    }

    private static long convertCanonicalIpv4ToLong(String ipv4) {
        String[] blocks = ipv4.split("\\.");
        String binaryRepresentation = Arrays.stream(blocks)
                .map(Integer::parseInt)
                .map(Integer::toBinaryString)
                .map(n -> addLeadingZeroes(n, 8))
                .collect(Collectors.joining());
        return Long.parseUnsignedLong(binaryRepresentation, 2);
    }

    private static long convertNotCanonicalIpv4ToLong(String ipv4) {
        List<Long> blocks;
        if (ipv4.contains(".")) {
            String[] splitBlocks = ipv4.split("\\.");
            blocks = Arrays.stream(splitBlocks)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        } else { // case when input is decimal ip representation
            return Long.parseLong(ipv4);
        }

        int neededLengthOfLastBlock = (4 - blocks.size() + 1) * 8; // to calculate what number of bits should take last block
        StringBuilder binaryRepresentation = new StringBuilder();
        int bSize = blocks.size();
        for (Long b:blocks) {
            String binaryStringWithoutLeadingZeroes = Long.toBinaryString(b);
            String binaryBlockWithLeadingZeroes;
            if (blocks.indexOf(b) == bSize - 1) {
                binaryBlockWithLeadingZeroes = addLeadingZeroes(binaryStringWithoutLeadingZeroes, neededLengthOfLastBlock);
            } else {
                binaryBlockWithLeadingZeroes = addLeadingZeroes(binaryStringWithoutLeadingZeroes, 8);
            }
            binaryRepresentation.append(binaryBlockWithLeadingZeroes);
        }
        return Long.parseUnsignedLong(binaryRepresentation.toString(), 2);
    }

    private static String addLeadingZeroes(String s, int lengthNeeded) {
        if (s.length() >= lengthNeeded){
            return s;
        }
        int zeroesNeeded = lengthNeeded - s.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i++ < zeroesNeeded;) {
            result.append(0);
        }
        result.append(s);
        return result.toString();
    }

    private static boolean isCanonicalRepresentation(String ipv4) {
        Pattern canonicalIpv4Pattern = Pattern
                .compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher matcher = canonicalIpv4Pattern.matcher(ipv4);
        return matcher.matches();
    }

    private static boolean isNotCanonicalRepresentation(String ipv4) {
        Pattern ipv4Pattern = Pattern
                .compile("^(\\d{1,5}\\.\\d{1,5}|\\d{1,5}(\\.\\d{1,3}){1,2}|\\d{1,3}\\d{1,5}\\d{1,3}|(\\d{1,3}\\.){1,2}\\d{1,5}|\\d{1,10}|(\\d{1,3}\\.){2}\\d{1,3})$");
        Matcher matcher = ipv4Pattern.matcher(ipv4);
        return matcher.matches();
    }
}
