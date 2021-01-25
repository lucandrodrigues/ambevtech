package br.com.ambevtech.weather.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.text.MessageFormat;
import java.util.Set;

@Getter
public class ServiceException extends RuntimeException {

    private EnumErrorException errorException;
    private String formattedDetalhe;
    private Set<ConstraintViolation<Object>> violations;

    public ServiceException(EnumErrorException error) {
        super(error.getNome());
        this.errorException = error;
        this.formattedDetalhe = error.getDetalhe();
    }

    public ServiceException(EnumErrorException error, Object[] args) {
        super(error.getNome());
        this.errorException = error;
        this.formattedDetalhe = MessageFormat.format(this.errorException.getDetalhe(), args);
    }

}
