package com.trivago.config.exception.errorhandler;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

public class ErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode()
                .is4xxClientError() ||
                response.getStatusCode()
                        .is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    response.getStatusCode();
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        ResponseErrorHandler.super.handleError(url, method, response);
    }
}
