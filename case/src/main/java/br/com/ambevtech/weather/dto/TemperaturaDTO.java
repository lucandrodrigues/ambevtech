package br.com.ambevtech.weather.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TemperaturaDTO implements Serializable {

    @SerializedName("day")
    private BigDecimal dia;

    @SerializedName("min")
    private BigDecimal minima;

    @SerializedName("max")
    private BigDecimal maxima;

}
