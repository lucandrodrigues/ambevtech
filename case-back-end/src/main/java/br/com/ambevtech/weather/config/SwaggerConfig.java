package br.com.ambevtech.weather.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private ApplicationYMLConfig config;

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        final List<ResponseMessage> globalResponses = Arrays.asList(
                new ResponseMessageBuilder().code(200).message("OK").build(),
                new ResponseMessageBuilder().code(201).message("Created").build(),
                new ResponseMessageBuilder().code(202).message("Accepted").build(),
                new ResponseMessageBuilder().code(204).message("No Content").build(),
                new ResponseMessageBuilder().code(400).message("Bad Request").build(),
                new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
                new ResponseMessageBuilder().code(403).message("Forbidden").build(),
                new ResponseMessageBuilder().code(404).message("Not Found").build(),
                new ResponseMessageBuilder().code(500).message("Internal Error").build());

        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Content-Type")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("application/json")
                .required(true)
                .build();

        List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.ant("/cidade/**")).build()
                .globalOperationParameters(aParameters)
                .useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, globalResponses)
                .globalResponseMessage(RequestMethod.POST, globalResponses)
                .globalResponseMessage(RequestMethod.PUT, globalResponses)
                .globalResponseMessage(RequestMethod.PATCH, globalResponses)
                .globalResponseMessage(RequestMethod.DELETE, globalResponses).apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("API de consulta da previsão do Tempo")
                .description("API para cadastro de cidades e consulta da previsão do tempo")
                .version("1.0.0")
                .contact(new Contact("Previsão do Tempo", "http://localhost:4200/", "lucandrodrigues@gmail.com"))
                .build();
    }


}
