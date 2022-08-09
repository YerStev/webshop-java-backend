package webservicees.webshop.model;

import org.springframework.http.HttpStatus;


public class ErrorResponse {
    private String messag;

    public ErrorResponse(HttpStatus code, String messag) {
        this.messag = messag;
    }

    public ErrorResponse() {

    }

    public String getMessag() {
        return messag;
    }
}
