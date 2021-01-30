package br.com.ambevtech.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties
@Getter
@Setter
public class ApplicationYMLConfig {

    @Autowired
    private SpringProperties spring;

    @Configuration
    @EnableConfigurationProperties
    @ConfigurationProperties(prefix = "spring")
    @Getter
    @Setter
    class SpringProperties {
        private Map<String, String> datasource;

        public Map<String, String> getDatasource() {
            return datasource;
        }

        public void setDatasource(Map<String, String> datasource) {
            this.datasource = datasource;
        }
    }
}
