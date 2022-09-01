package webservicees.webshop.exceptions;

import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class IdNotFoundException extends WebshopException {

    public IdNotFoundException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }

}
