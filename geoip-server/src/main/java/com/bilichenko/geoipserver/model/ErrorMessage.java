package com.bilichenko.geoipserver.model;

import lombok.Data;

@Data
public class ErrorMessage {
    private Integer statusCode;
    private String message;
}
