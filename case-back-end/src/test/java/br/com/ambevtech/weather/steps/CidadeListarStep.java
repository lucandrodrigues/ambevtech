package br.com.ambevtech.weather.steps;

import br.com.ambevtech.weather.SpringIntegrationTest;
import br.com.ambevtech.weather.dto.CidadeDTO;
import br.com.ambevtech.weather.dto.CidadeTesteDTO;
import br.com.ambevtech.weather.dto.FiltroDTO;
import br.com.ambevtech.weather.utils.Validators;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;

public class CidadeListarStep extends SpringIntegrationTest {

    private Response response;
    private FiltroDTO<CidadeTesteDTO> filtro;
    private CidadeTesteDTO dto;
    private String cenario = "Listar cidades";

    @Dado("^que possuo um campo para buscar uma cidade \"([^\"]*)\"$")
    public void quePossuoUmCampoParaBuscarUmaCidade(String nome) throws Throwable {
        dto = new CidadeTesteDTO(nome);
        filtro = new FiltroDTO<>();
        filtro.setObj(dto);
    }

    @Quando("^confirmar a pesquisa$")
    public void confirmarAPesquisa() throws Throwable {
        response = postMessage(filtro, "/cidade/listar");
    }

    @Então("^deve me retornar a quantidade de registros \"([^\"]*)\"$")
    public void deveMeRetornarAQuantidadeDeRegistros(int quantidade) throws Throwable {
        Validators.validaQuantidadeRegistros(cenario, response, quantidade);
    }

}
