package br.com.ambevtech.weather.dto.previsao;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @SerializedName("pop")
    private BigDecimal chuva;

    public String getDiaDaSemana() {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(new Date(this.data * 1000L));
    }

}
