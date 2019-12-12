package com.bilichenko.geoipserver.controller.exceptions;

public class Ip2LocationNotFoundException extends RuntimeException {

    private String ipv4;

    public Ip2LocationNotFoundException(String message, String ipv4) {
        super(message);
        this.ipv4 = ipv4;
    }

    public String getIpv4() {
        return ipv4;
    }
}
