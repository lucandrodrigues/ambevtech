package br.com.ambevtech.weather;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
@ActiveProfiles("teste")
public class SpringIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(SpringIntegrationTest.class);

    @LocalServerPort
    protected int port;

    protected static String url = "http://127.0.0.1:8080";

    @Autowired
    private Flyway flyway;

    public static Flyway staticFlyway;

    @PostConstruct
    public void init() {
        SpringIntegrationTest.staticFlyway = flyway;
    }

    protected static Headers getHeaders() {
        Header h1 = new Header("Content-type", ContentType.APPLICATION_JSON.toString());
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        Headers headers = new Headers(list);
        return headers;
    }

    protected static Response postMessage(Object body, String uri) {
        logger.info("Post url " + url + uri);
        return RestAssured.given().headers(getHeaders()).when().body(body).post(url + uri);
    }

    protected static Response postMessage(Object body, String uri, Map<String, ?> parametersMap) {
        logger.info("Post url " + url + uri);
        return RestAssured.given().headers(getHeaders()).queryParams(parametersMap).when().body(body).post(url + uri);
    }

    protected static Response getMessage(Map<String, Object> parametersMap, String uri) {
        logger.info("Get url " + url + uri);
        if (ObjectUtils.isEmpty(parametersMap)) {
            return RestAssured.given().headers(getHeaders()).when().get(url + uri);
        } else {
            return RestAssured.given().headers(getHeaders()).params(parametersMap).when().get(url + uri);
        }
    }
}