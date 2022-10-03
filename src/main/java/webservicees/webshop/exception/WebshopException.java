package webservicees.webshop.exception;

import org.springframework.http.HttpStatus;

public class WebshopException extends RuntimeException{
    private String message;
    private HttpStatus statusCode;

    public WebshopException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public WebshopException() {
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

}
