package webservicees.webshop.exception;

import org.springframework.http.HttpStatus;

public class IdNotFoundException extends WebshopException {

    public IdNotFoundException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }

}
