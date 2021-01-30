package br.com.ambevtech.weather.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorException extends Exception {

    private static final long serialVersionUID = 1L;

    private String messageException;
    private String stackTraceException;
    private List<String> errors;

    public ErrorException(String messageException) {
        this.messageException = messageException;
    }

    public ErrorException(String messageException, List<String> errors) {
        this.messageException = messageException;
        this.errors = errors;
    }

    public Response getResponse() {
        Response response = new Response();
        response.setMessage(messageException);
        response.setStackTrace(stackTraceException);
        response.setErrors(errors);
        return response;
    }
}
