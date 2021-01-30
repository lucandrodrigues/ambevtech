package br.com.ambevtech.weather.resource;

import br.com.ambevtech.weather.dto.CidadeDTO;
import br.com.ambevtech.weather.dto.FiltroDTO;
import br.com.ambevtech.weather.dto.previsao.PrevisaoDTO;
import br.com.ambevtech.weather.exception.ServiceException;
import br.com.ambevtech.weather.exception.ValidMessages;
import br.com.ambevtech.weather.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cidade")
@Api("/cidade")
@CrossOrigin()
public class CidadeResource {

    private final CidadeService service;

    @Autowired
    public CidadeResource(CidadeService service) {
        this.service = service;
    }

    @PostMapping("/listar")
    @ApiOperation(value = "Listar cidades", response = CidadeDTO.class, notes = "Retorna as cidades cadastradas")
    public ResponseEntity<?> listarCidades(@Valid @ApiParam(value = "Nome da cidade e valores da paginação") @RequestBody FiltroDTO<CidadeDTO> filtro, BindingResult bindingResult) throws ServiceException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidMessages.retornaMensagemErro(bindingResult));
        }
        return ResponseEntity.ok(service.listarCidades(filtro));
    }

    @PostMapping
    @ApiOperation(value = "Cadastrar cidade", response = CidadeDTO.class, notes = "Cadastra uma cidade para poder visualizar sua previsão do tempo")
    public ResponseEntity<?> cadastrarCidade(@Valid @ApiParam(value = "Parâmetros do cadastro da cidade") @RequestBody CidadeDTO dto, BindingResult bindingResult) throws ServiceException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidMessages.retornaMensagemErro(bindingResult));
        }
        return ResponseEntity.ok(service.cadastrarCidade(dto.getNome()));
    }

    @GetMapping("/previsao/{id}")
    @ApiOperation(value = "Consultar Previsão do Tempo", response = PrevisaoDTO.class, notes = "Retorna a previsão do tempo a partir do identificador da cidade")
    public ResponseEntity<?> consultarPrevisao(@ApiParam(value = "Identificador da cidade") @PathVariable("id") Integer id) throws ServiceException {
        return ResponseEntity.ok(service.consultarPrevisao(id));
    }

}
