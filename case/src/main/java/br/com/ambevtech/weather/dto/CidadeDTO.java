package br.com.ambevtech.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO implements Serializable {

    private Integer id;
    private String nome;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public CidadeDTO(String nome, Map<String, BigDecimal> coordenadas){
        this.setNome(nome);
        this.setLatitude(coordenadas.get("lat"));
        this.setLongitude(coordenadas.get("lon"));
    }

}
