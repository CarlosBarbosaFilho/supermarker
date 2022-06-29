package br.com.customer.service.exception;

public class CustomerFraudException extends RuntimeException {

    public CustomerFraudException(String msg) {
        super(msg);
    }
}
