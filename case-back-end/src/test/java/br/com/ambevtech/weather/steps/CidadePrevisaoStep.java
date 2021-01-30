package br.com.ambevtech.weather.steps;

import br.com.ambevtech.weather.SpringIntegrationTest;
import br.com.ambevtech.weather.utils.Validators;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;

public class CidadePrevisaoStep extends SpringIntegrationTest {

    private Response response;
    private Integer cidadeId;
    private String cenario = "Consultar Previsão do Tempo";

    @Dado("^que possuo uma cidade para consultar a previsão \"([^\"]*)\"$")
    public void quePossuoUmaCidadeParaConsultarAPrevisão(Integer id) throws Throwable {
        cidadeId = id;
    }

    @Quando("^confirmar o registro$")
    public void confirmarORegistro() throws Throwable {
        response = getMessage(null, "/cidade/previsao/"+ cidadeId);
    }

    @Então("^deve me retornar um status de \"([^\"]*)\"$")
    public void deveMeRetornarUmStatusDe(String status) throws Throwable {
        Validators.validaStatus(cenario, response, status);
    }

}
