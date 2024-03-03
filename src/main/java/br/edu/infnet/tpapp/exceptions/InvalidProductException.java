package br.edu.infnet.tpapp.exceptions;

public class InvalidProductException extends Exception {

    public InvalidProductException() {};

    public InvalidProductException(String errorMessage) {
        super(errorMessage);
    }

}
