package br.com.ambevtech.weather.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@Setter
public class FiltroDTO<T> implements Serializable {

    private T obj;

    private int page = 0;

    private int size = 7;

    private Sort sort;

}
