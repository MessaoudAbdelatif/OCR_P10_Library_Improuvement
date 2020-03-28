package com.publicservice.v1.exception;

import com.publicservice.business.exception.ExtraTimeNotAllowed;
import com.publicservice.business.exception.LibraryUserNotFoundException;
import com.publicservice.v1.dto.model.ErrorMessageDto;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {LibraryUserNotFoundException.class})
  public ResponseEntity<Object> handleLibraryUserNotFoundException(LibraryUserNotFoundException ex,
      WebRequest request) {
    ErrorMessageDto errorMessageDto = new ErrorMessageDto(new Date(), ex.getMessage());
    return new ResponseEntity<>(errorMessageDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex,
      WebRequest request) {
    ErrorMessageDto errorMessageDto = new ErrorMessageDto(new Date(), ex.getMessage());
    return new ResponseEntity<>(errorMessageDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {ExtraTimeNotAllowed.class})
  public ResponseEntity<Object> handleExtraTimeNotAllowed(ExtraTimeNotAllowed ex,
      WebRequest request) {
    ErrorMessageDto errorMessageDto = new ErrorMessageDto(new Date(), ex.getMessage());
    return new ResponseEntity<>(errorMessageDto, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
  }



}

