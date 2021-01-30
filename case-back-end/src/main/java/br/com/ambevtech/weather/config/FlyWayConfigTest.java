package br.com.ambevtech.weather.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("teste")
public class FlyWayConfigTest {

    @Autowired
    private ApplicationYMLConfig ymlConfig;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        ymlConfig.getSpring().getDatasource().get("url"),
                        ymlConfig.getSpring().getDatasource().get("username"),
                        ymlConfig.getSpring().getDatasource().get("password"))
                .baselineDescription("Criação do banco de dados de teste")
                .baselineOnMigrate(true)
                .outOfOrder(false)
                .schemas(ymlConfig.getSpring().getDatasource().get("database"))
                .locations("classpath:/db/migration", "classpath:/db/test")
                .placeholderReplacement(false)
                .load();

        flyway.clean();
        return flyway;
    }
}
