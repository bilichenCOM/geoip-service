package com.bilichenko.geoipserver.controller.advice;

import com.bilichenko.geoipserver.controller.exceptions.Ip2LocationNotFoundException;
import com.bilichenko.geoipserver.model.ErrorMessage;
import com.bilichenko.geoipserver.utils.exceptions.NotValidIpv4Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Ip2LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorMessage handleIp2LocationNotFoundException(Ip2LocationNotFoundException e) {
        ErrorMessage errMessage = new ErrorMessage();
        errMessage.setStatusCode(404);
        errMessage.setMessage("no location matched with ip: " + e.getIpv4());
        return errMessage;
    }

    @ExceptionHandler(NotValidIpv4Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorMessage handleNotValidIpException(NotValidIpv4Exception e) {
        ErrorMessage errMessage = new ErrorMessage();
        errMessage.setStatusCode(400);
        errMessage.setMessage("wrong ipv4 address was passed - " + e.getIpv4NotValid());
        return errMessage;
    }
}
