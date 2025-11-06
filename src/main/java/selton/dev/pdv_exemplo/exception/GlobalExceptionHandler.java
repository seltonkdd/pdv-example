package selton.dev.pdv_exemplo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import selton.dev.pdv_exemplo.exception.custom.CpfInvalidoException;
import selton.dev.pdv_exemplo.exception.custom.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorMessage response = new ErrorMessage(
            LocalDateTime.now(), 
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
        );
        return new ResponseEntity<ErrorMessage>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<ErrorMessage> handleCpfInvalidoException(CpfInvalidoException ex) {
        ErrorMessage response = new ErrorMessage(
            LocalDateTime.now(), 
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage()
        );
        return new ResponseEntity<ErrorMessage>(response, HttpStatus.BAD_REQUEST);
    }
}
