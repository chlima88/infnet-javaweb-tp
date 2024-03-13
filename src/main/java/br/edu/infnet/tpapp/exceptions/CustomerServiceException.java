package br.edu.infnet.tpapp.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerServiceException extends Exception {

    public CustomerServiceException(String message) {
        super(message);
    }
}
