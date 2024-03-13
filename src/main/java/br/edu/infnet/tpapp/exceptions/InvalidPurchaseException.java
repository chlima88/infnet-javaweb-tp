package br.edu.infnet.tpapp.exceptions;

public class InvalidPurchaseException extends Exception {

    public InvalidPurchaseException(String errorMessage) {
        super(errorMessage);
    }

}
