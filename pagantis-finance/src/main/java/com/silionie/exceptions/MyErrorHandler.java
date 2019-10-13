package com.silionie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class MyErrorHandler extends DefaultResponseErrorHandler {
  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    HttpStatus statusCode = HttpStatus.resolve(response.getRawStatusCode());
    if (statusCode == null) {
      throw new TransferException("", null);
    }
    handleError(response, statusCode);
  }
}