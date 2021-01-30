package br.com.ambevtech.weather.steps;

import br.com.ambevtech.weather.SpringIntegrationTest;
import br.com.ambevtech.weather.dto.CidadeTesteDTO;
import br.com.ambevtech.weather.utils.Validators;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;

public class CidadeSalvarStep extends SpringIntegrationTest {

    private Response response;
    private CidadeTesteDTO dto;
    private String cenario = "Salvar uma cidade";

    @Dado("^que possuo uma cidade para salvar \"([^\"]*)\"$")
    public void quePossuoUmaCidadeParaSalvar(String nome) throws Throwable {
        dto = new CidadeTesteDTO(nome);
    }

    @Quando("^salvar o resgitro$")
    public void salvarOResgitro() throws Throwable {
        response = postMessage(dto, "/cidade");
    }

    @Então("^deve me retornar um status \"([^\"]*)\"$")
    public void deveMeRetornarUmStatus(String status) throws Throwable {
        Validators.validaStatus(cenario, response, status);
    }

}
