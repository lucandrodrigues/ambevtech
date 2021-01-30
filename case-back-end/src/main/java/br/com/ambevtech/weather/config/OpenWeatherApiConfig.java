package br.com.ambevtech.weather.config;

import br.com.ambevtech.weather.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherApiConfig {

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public OpenWeatherApiConfig(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public RestTemplate getRestTemplate() {
        return restTemplateBuilder
                .rootUri(Constantes.OpenWeatherMap.URL_API)
                .build();
    }
}
