package br.com.ambevtech.weather.service;

import br.com.ambevtech.weather.config.OpenWeatherApiConfig;
import br.com.ambevtech.weather.dto.CidadeDTO;
import br.com.ambevtech.weather.dto.PrevisaoDTO;
import br.com.ambevtech.weather.entity.Cidade;
import br.com.ambevtech.weather.util.Constantes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class OpenWeatherApiService {

    private OpenWeatherApiConfig openWeatherApiConfig;

    @Autowired
    public OpenWeatherApiService(OpenWeatherApiConfig openWeatherApiConfig) {
        this.openWeatherApiConfig = openWeatherApiConfig;
    }

    public CidadeDTO buscarCidade(String nome) {
        ResponseEntity<String> response = null;
        String url = "/data/2.5/weather" +
                "?appid=" + Constantes.OpenWeatherMap.APPID +
                "&q=" + nome;
        response = openWeatherApiConfig.getRestTemplate().getForEntity(url, String.class);

        Gson gson = new Gson();
        JsonObject objRetornoResponse = gson.fromJson(response.getBody(), JsonObject.class);

        Type type = new TypeToken<Map<String, BigDecimal>>(){}.getType();
        Map<String, BigDecimal> coordenadas = gson.fromJson(objRetornoResponse.get("coord"), type);

        return new CidadeDTO(nome, coordenadas);
    }

    public PrevisaoDTO buscarPrevisao(Cidade cidade) {
        ResponseEntity<String> response = null;
        String url = "/data/2.5/onecall" +
                "?appid=" + Constantes.OpenWeatherMap.APPID +
                "&lang=" + Constantes.OpenWeatherMap.LANG +
                "&units=" + Constantes.OpenWeatherMap.UNITS +
                "&exclude=current,minutely,hourly,alerts" +
                "&lat=" + cidade.getLatitude() +
                "&lon=" + cidade.getLongitude();
        response =  openWeatherApiConfig.getRestTemplate().getForEntity(url, String.class);

        Gson gson = new Gson();
        return gson.fromJson(response.getBody(), PrevisaoDTO.class);
    }

}
