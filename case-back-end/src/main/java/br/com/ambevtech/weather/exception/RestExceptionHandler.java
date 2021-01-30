package br.com.ambevtech.weather.exception;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException exception) {

        if (ObjectUtils.isEmpty(exception.getViolations())) {
            JsonObject response = new JsonObject();
            response.addProperty("tipo", exception.getErrorException().getTipo());
            response.addProperty("titulo", exception.getErrorException().getNome());
            response.addProperty("descricao", exception.getFormattedDetalhe());
            return new ResponseEntity<>(new Gson().toJson(response), exception.getErrorException().getHttpStatus());
        }

        List<JsonObject> errors = new ArrayList<>();
        for (ConstraintViolation<Object> violation : exception.getViolations()) {
            JsonObject response = new JsonObject();
            response.addProperty("tipo", exception.getErrorException().getTipo());
            response.addProperty("titulo", exception.getErrorException().getNome());
            response.addProperty("descricao", StringUtils.capitalize(violation.getPropertyPath().toString()) + " " + violation.getMessage());
            errors.add(response);
        }
        return new ResponseEntity<>(new Gson().toJson(errors.size() > 1 ? errors : errors.get(0)), exception.getErrorException().getHttpStatus());
    }

    @ExceptionHandler(value = ErrorException.class)
    public ResponseEntity<Response> handleException(ErrorException exception) {
        return ResponseEntity.badRequest().body(exception.getResponse());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        Response response = new Response("Violação de Restrição");
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.add("O campo " + error.getField() + ": " + error.getDefaultMessage() + System.lineSeparator()));
        response.setErrors(errors);
        return ResponseEntity.badRequest().body(response);
    }
}