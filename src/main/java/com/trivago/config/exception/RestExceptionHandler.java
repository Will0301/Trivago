package com.trivago.config.exception;

import com.trivago.config.exception.badrequest.BadRequestExceptionDetails;
import com.trivago.config.exception.notfound.NotFoundExceptionDetails;
import com.trivago.config.exception.servererror.ForbiddenErrorDetails;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public BadRequestExceptionDetails badRequest(MethodArgumentNotValidException argumentNotValidException){
        Map<String, String> error = new HashMap<>();
        List<FieldError> errorList = Collections.emptyList();
        return BadRequestExceptionDetails.builder()
                .status(BAD_REQUEST.value())
                .title("Invalid Request.")
                .timestamp(Instant.now())
                .details(error)
                .developerMessage("Error! Check for incorrect information.")
                .build();
    }
    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    public BadRequestExceptionDetails badRequestBindException(BindException argumentNotValidException){
        Map<String, String> error = new HashMap<>();
        List<FieldError> errorList = argumentNotValidException.getBindingResult().getFieldErrors();
        errorList.forEach(p -> error.put(p.getField(), p.getDefaultMessage()));

        return BadRequestExceptionDetails.builder()
                .status(BAD_REQUEST.value())
                .title("Invalid Request.")
                .timestamp(Instant.now())
                .details(error)
                .developerMessage("Error! Check for incorrect information.")
                .build();
    }
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(BAD_REQUEST)
    public BadRequestExceptionDetails badRequest2(WebExchangeBindException argumentNotValidException){
        Map<String, String> error = new HashMap<>();
        List<FieldError> errorList = argumentNotValidException.getBindingResult().getFieldErrors();
        errorList.forEach(p -> error.put(p.getField(), p.getDefaultMessage()));

        return BadRequestExceptionDetails.builder()
                .status(BAD_REQUEST.value())
                .title("Invalid Request.")
                .timestamp(Instant.now())
                .details(error)
                .developerMessage("Error! Check for incorrect information.")
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public NotFoundExceptionDetails notFound(){
        return NotFoundExceptionDetails.builder()
                .time(LocalDateTime.now())
                .status(NOT_FOUND.value())
                .title("Not Found")
                .details("We could not find this endpoint")
                .build();
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ForbiddenErrorDetails servererror(){
        return ForbiddenErrorDetails.builder()
                .title("Forbidden")
                .time(LocalDateTime.now())
                .status(FORBIDDEN.value())
                .details("Please verify your API key, API host or if you are subscribed to this API.")
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public BadRequestExceptionDetails badRequestExceptionDetails(){
        return BadRequestExceptionDetails.builder()
                .title("Bad Request")
                .timestamp(Instant.now())
                .status(BAD_REQUEST.value())
                .developerMessage("The host you've provided is invalid. If you have difficulties, contact the RapidAPI support team, support@rapidapi.com")
                .build();
    }
}
