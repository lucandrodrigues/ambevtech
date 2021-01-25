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
public class PrevisaoDTO implements Serializable {

    private String nomeCidade;

    @SerializedName("timezone_offset")
    private Long fuso;

    @SerializedName("daily")
    private List<DiaDTO> dias;

}

