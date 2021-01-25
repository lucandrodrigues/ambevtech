package br.com.ambevtech.weather.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DiaDTO implements Serializable {

    @SerializedName("dt")
    Long data;

    @SerializedName("temp")
    TemperaturaDTO temperatura;

    @SerializedName("weather")
    List<ClimaDTO> climas;

}
