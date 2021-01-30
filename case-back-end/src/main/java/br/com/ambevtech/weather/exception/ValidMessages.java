package br.com.ambevtech.weather.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidMessages {

    public static String retornaMensagemErro(BindingResult bindingResult) {
        StringBuilder string = new StringBuilder();
        List<ObjectError> errors = bindingResult.getAllErrors();
        FieldError erro = null;
        for (ObjectError objectError : errors) {
            if (objectError instanceof FieldError) {
                erro = (FieldError) objectError;
                string.append("O campo " + erro.getField().toUpperCase() + ": " + objectError.getDefaultMessage() + "\n");
            } else {
                string.append(objectError.getDefaultMessage() + "\n");
            }
        }
        return string.toString();
    }

}