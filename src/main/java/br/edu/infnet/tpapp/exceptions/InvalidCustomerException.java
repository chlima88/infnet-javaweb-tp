package br.edu.infnet.tpapp.exceptions;

public class InvalidCustomerException extends Exception {

    public InvalidCustomerException() {}

    public InvalidCustomerException(String errorMessage) {
        super(errorMessage);
    }
}