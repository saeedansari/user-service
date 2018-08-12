package com.test.userservice.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserServiceExceptionHandler {


  @ExceptionHandler(value = {IllegalArgumentException.class})
  protected ResponseEntity<Object> handleException(IllegalArgumentException e){
    UserServiceException apiError = new UserServiceException(e.getMessage(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {UserServiceException.class})
  protected ResponseEntity<ApiError> handleException(UserServiceException e){
    ApiError apiError = new ApiError(e.getMessage(), e.getHttpStatus());
    return new ResponseEntity<>(apiError, e.getHttpStatus());
  }



}
