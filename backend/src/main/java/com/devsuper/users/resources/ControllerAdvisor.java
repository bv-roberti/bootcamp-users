package com.devsuper.users.resources;

import com.devsuper.users.exceptions.EntityNotFoundException;
import com.devsuper.users.resources.exceptions.StandardError;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

  @ExceptionHandler(com.devsuper.users.exceptions.EntityNotFoundException.class)
  public ResponseEntity<StandardError> EntityNotFoundException(
      EntityNotFoundException e, HttpServletRequest request) {

    HttpStatus _status = HttpStatus.NOT_FOUND;
    StandardError err =
        new StandardError(
            Instant.now(),
            _status.value(),
            "Entity not found",
            e.getMessage(),
            request.getRequestURI());

    return ResponseEntity.status(_status).body(err);
  }
}
