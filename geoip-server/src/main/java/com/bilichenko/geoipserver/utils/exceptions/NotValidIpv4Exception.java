package com.bilichenko.geoipserver.utils.exceptions;

public class NotValidIpv4Exception extends RuntimeException {

    private String ipv4NotValid;

    public NotValidIpv4Exception(String ipv4NotValid) {
        this.ipv4NotValid = ipv4NotValid;
    }

    public String getIpv4NotValid() {
        return ipv4NotValid;
    }
}
