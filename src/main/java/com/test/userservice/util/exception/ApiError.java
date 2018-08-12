package com.test.userservice.util.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {

  private String message;
  private HttpStatus httpStatus;

  public ApiError(String message, HttpStatus status) {
    this.message = message;
    this.httpStatus = status;
  }

}
