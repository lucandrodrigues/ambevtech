package br.com.ambevtech.weather.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class Response {

    private String message;
    private List<String> errors;
    private String stackTrace;

    public Response(String message) {
        this.message = message;
    }
}
