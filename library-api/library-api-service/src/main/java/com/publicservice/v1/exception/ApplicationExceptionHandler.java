package com.publicservice.v1.exception;

import com.publicservice.business.exception.LibraryUserNotFoundException;
import com.publicservice.v1.dto.model.ErrorMessageDto;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(value = {LibraryUserNotFoundException.class})
  public ResponseEntity<Object> handleLibraryUserNotFoundException(LibraryUserNotFoundException ex,
      WebRequest request) {
    ErrorMessageDto errorMessageDto = new ErrorMessageDto(new Date(), ex.getMessage());
    return new ResponseEntity<>(errorMessageDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex,
      WebRequest request) {
    ErrorMessageDto errorMessageDto = new ErrorMessageDto(new Date(), "Can't find !! please try again...");
    return new ResponseEntity<>(errorMessageDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

}

