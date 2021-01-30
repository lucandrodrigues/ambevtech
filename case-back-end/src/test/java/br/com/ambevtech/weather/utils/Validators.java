package br.com.ambevtech.weather.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import javassist.expr.NewArray;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

public class Validators {

    public static String getErrorByResponse(Response response) {

        String mensagem = response.getBody().asString();

        return "HTTP: " + response.getStatusCode() + System.lineSeparator() +
                "Body: " + System.lineSeparator() + mensagem;
    }

    public static void validaMensagemBody(Response response, String mensagem, String cenario) {

        String body = response.getBody().asString();
        Assert.isTrue(!ObjectUtils.isEmpty(body) && body.contains(mensagem), "Falha no cenário: " + cenario + " mensagem: " + mensagem + " não encontrada." +
                System.lineSeparator() + "HTTP: " + response.getStatusCode() +
                System.lineSeparator() + "Body: " + body);
    }

    public static void validaStatus(String cenario, Response response, String status) {
        Assert.isTrue(response.statusCode() == Integer.parseInt(status),
                    "Falha no cenário " + cenario + System.lineSeparator() +
                            ", o teste retornou: " + Validators.getErrorByResponse(response));
    }

    public static void validaQuantidadeRegistros(String cenario, Response response, int quantidade) {
        JsonPath jsonPathEvaluator = response.getBody().jsonPath();
        Assert.isTrue(jsonPathEvaluator.get("totalElements").equals(quantidade),
                "Falha no cenário " + cenario + System.lineSeparator() +
                        ", o teste retornou: " + Validators.getErrorByResponse(response));
    }
}
