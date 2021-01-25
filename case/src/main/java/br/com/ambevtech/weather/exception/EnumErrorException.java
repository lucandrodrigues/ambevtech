package br.com.ambevtech.weather.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EnumErrorException {

    PARAMETROS_INVALIDOS(HttpStatus.BAD_REQUEST,
            "Erro",
            "Corpo e/ou parametros da requisição inválido(s).",
            "{0}"),

    ERRO_INTERNO(HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro",
            "Erro interno.",
            "Não foi possível realizar operação: {0}")
    ;

    private HttpStatus httpStatus;
    private String nome;
    private String detalhe;
    private String tipo;

    EnumErrorException(HttpStatus httpStatus, String tipo, String nome, String detalhe) {
        this.httpStatus = httpStatus;
        this.tipo = tipo;
        this.nome = nome;
        this.detalhe = detalhe;
    }

}
