package br.com.ambevtech.weather.service;

import br.com.ambevtech.weather.config.CacheNames;
import br.com.ambevtech.weather.config.OpenWeatherApiConfig;
import br.com.ambevtech.weather.dto.CidadeDTO;
import br.com.ambevtech.weather.dto.previsao.DiaDTO;
import br.com.ambevtech.weather.dto.previsao.PrevisaoDTO;
import br.com.ambevtech.weather.entity.Cidade;
import br.com.ambevtech.weather.exception.EnumErrorException;
import br.com.ambevtech.weather.exception.ServiceException;
import br.com.ambevtech.weather.util.Constantes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OpenWeatherApiService {

    private final OpenWeatherApiConfig openWeatherApiConfig;
    private final Logger logger = LoggerFactory.getLogger(OpenWeatherApiService.class);

    @Autowired
    public OpenWeatherApiService(OpenWeatherApiConfig openWeatherApiConfig) {
        this.openWeatherApiConfig = openWeatherApiConfig;
    }

    private ResponseEntity<String> buscarCidadeApi(String nome) throws ServiceException {
        try {
            String uri = "/data/2.5/weather?appid=" + Constantes.OpenWeatherMap.APPID + "&q=" + nome;
            logger.info("Consultando previsão em OpenWeatherApi para a cidade: " + nome);
            ResponseEntity<String> response = openWeatherApiConfig.getRestTemplate().getForEntity(uri, String.class);
            logger.info("Consulta realizada: " + response.toString());

            return response;
        } catch (HttpClientErrorException e) {
            logger.error("Cidade não localizada em OpenWeatherApi: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Falha ao consultar OpenWeatherApi: " + e.getMessage());
            throw e;
        }

    }

    @Cacheable(value = CacheNames.cacheCidade, key = "{#nome}")
    public CidadeDTO buscarCidade(String nome) {
        ResponseEntity<String> response = buscarCidadeApi(nome);

        Gson gson = new Gson();
        JsonObject objRetornoResponse = gson.fromJson(response.getBody(), JsonObject.class);

        Type type = new TypeToken<Map<String, BigDecimal>>() {}.getType();
        Map<String, BigDecimal> coordenadas = gson.fromJson(objRetornoResponse.get("coord"), type);
        nome = objRetornoResponse.get("name").toString().replaceAll("\"", "");

        return new CidadeDTO(nome, coordenadas);
    }

    public PrevisaoDTO buscarPrevisao(Cidade cidade) throws ServiceException {
        try {
            String uri = buildURIPrevisao(cidade);
            logger.info("Consultando previsão diária em OpenWeatherApi para a cidade: " + cidade.getNome());
            ResponseEntity<String> response = openWeatherApiConfig.getRestTemplate().getForEntity(uri, String.class);
            logger.info("Consulta realizada: " + response.toString());

            Gson gson = new Gson();
            PrevisaoDTO previsao = gson.fromJson(response.getBody(), PrevisaoDTO.class);
            previsao.setNomeCidade(cidade.getNome());
            previsao.setDias(limitarDias(previsao.getDias(), Constantes.DIAS_PREVISAO));

            return previsao;
        } catch (Exception e) {
            logger.error("Falha ao consultar OpenWeatherApi: " + e.getMessage());
            throw new ServiceException(EnumErrorException.ERRO_INTERNO, new Object[]{e.getMessage()});
        }
    }

    private String buildURIPrevisao(Cidade cidade) {
        return "/data/2.5/onecall" +
                "?appid=" + Constantes.OpenWeatherMap.APPID +
                "&lang=" + Constantes.OpenWeatherMap.LANG +
                "&units=" + Constantes.OpenWeatherMap.UNITS +
                "&exclude=current,minutely,hourly,alerts" +
                "&lat=" + cidade.getLatitude() +
                "&lon=" + cidade.getLongitude();
    }

    private List<DiaDTO> limitarDias(List<DiaDTO> dias, int limite) {
        if (dias.size() > limite) {
            dias.subList(limite, dias.size()).clear();
        }
        return dias;
    }

}