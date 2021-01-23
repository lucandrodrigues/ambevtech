package br.com.ambevtech.weather.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!teste")
public class FlywayConfig {

    @Autowired
    private ApplicationYMLConfig ymlConfig;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = Flyway.configure().dataSource(ymlConfig.getSpring().getDatasource().get("url"),
                ymlConfig.getSpring().getDatasource().get("username"),
                ymlConfig.getSpring().getDatasource().get("password"))
                .baselineDescription("Migração do banco de dados...")
                .baselineOnMigrate(true)
                .schemas(ymlConfig.getSpring().getDatasource().get("database"))
                .outOfOrder(true)
                .validateOnMigrate(false)
                .cleanDisabled(true)
                .placeholderReplacement(false)
                .load();

        flyway.repair();
        flyway.baseline();

        return flyway;
    }
}
