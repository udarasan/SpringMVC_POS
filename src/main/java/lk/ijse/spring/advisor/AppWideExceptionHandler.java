package lk.ijse.spring.advisor;

import lk.ijse.spring.exception.NotFoundException;
import lk.ijse.spring.exception.ValidateException;
import lk.ijse.spring.util.StandradResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppWideExceptionHandler {
    //    client case = 400;
//    server case = 500;

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        return new ResponseEntity(new StandradResponse("500", "Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException e) {
        return new ResponseEntity(new StandradResponse("404", "Error", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity handleValidationException(ValidateException e) {
        return new ResponseEntity(new StandradResponse("400", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
