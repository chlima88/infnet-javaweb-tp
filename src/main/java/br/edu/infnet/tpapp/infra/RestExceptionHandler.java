package br.edu.infnet.tpapp.infra;

import br.edu.infnet.tpapp.domain.model.Purchase;
import br.edu.infnet.tpapp.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CustomerServiceException.class, ProductServiceException.class, PurchaseServiceException.class})
    private ResponseEntity<Object> badRequestException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(
                        HttpStatus.BAD_REQUEST,
                        exception.getClass().getSimpleName() + ": " + exception.getMessage()
                ));
    }

    @ExceptionHandler({CustomerNotFoundException.class, ProductNotFoundException.class})
    private ResponseEntity<Object> notFoundException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RestErrorMessage(
                        HttpStatus.NOT_FOUND,
                        exception.getClass().getSimpleName() + ": " + exception.getMessage()
                ));
    }

    @ExceptionHandler({InvalidPurchaseException.class, InvalidCustomerException.class, InvalidProductException.class})
    private ResponseEntity<Object> invalidDataException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestErrorMessage(
                        HttpStatus.BAD_REQUEST,
                        exception.getClass().getSimpleName() + ": " + exception.getMessage()
                ));
    }

}
