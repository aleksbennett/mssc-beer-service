package guru.springframework.msscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {
    
    /* handle validation errors and return to user */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<?>> validationErrorHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        
        e.getConstraintViolations().forEach(error -> errors.add(error.toString()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}