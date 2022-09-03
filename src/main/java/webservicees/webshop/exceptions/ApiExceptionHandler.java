package webservicees.webshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<?> handleErrors (HttpServletRequest request, Throwable e){
        HttpStatus code;
        System.out.println(e.getMessage());
        System.out.println(e);

     if(e instanceof  WebshopException)
         code = ((WebshopException) e).getStatusCode();
     else if (e instanceof  IllegalArgumentException)
        code = HttpStatus.BAD_REQUEST;
     // weitere Exceptions mit else if prüfen
     else{
         code = HttpStatus.INTERNAL_SERVER_ERROR;
     }
        return new ResponseEntity<>(new ErrorInfo(e.getMessage(),request.getRequestURI()), code);
    }

    public class ErrorInfo{
        private String error;
        private String requestUri;

        public ErrorInfo(String error, String requestURI) {
            this.error = error;
            this.requestUri = requestURI;
        }

        public ErrorInfo() {
        }

        public String getError() {
            return error;
        }

        public String getRequestUri() {
            return requestUri;
        }
    }
}
