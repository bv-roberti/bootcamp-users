package com.devsuper.users.exceptions;

import com.devsuper.users.resources.StandardError;
import java.time.Instant;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourcesExceptionHandler {

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<StandardError> Database(
      EntityNotFoundException e, HttpServletRequest request) {

    HttpStatus _status = HttpStatus.BAD_REQUEST;
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
