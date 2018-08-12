package com.test.userservice.util.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserServiceException extends RuntimeException {

  private String message;
  private HttpStatus httpStatus;

  public UserServiceException(String message, HttpStatus status) {
    this.message = message;
    this.httpStatus = status;
  }


}
