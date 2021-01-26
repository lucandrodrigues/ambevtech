package br.com.ambevtech.weather.dto;

import br.com.ambevtech.weather.util.Constantes;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ClimaDTO implements Serializable {

    @SerializedName("description")
    private String descricao;

    @SerializedName("icon")
    private String icone;

    public String getIcone() {
        return Constantes.OpenWeatherMap.URL_BASE + "/img/wn/" + this.icone + "@2x.png";
    }

}
