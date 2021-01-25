package br.com.ambevtech.weather.resource;

import br.com.ambevtech.weather.dto.CidadeDTO;
import br.com.ambevtech.weather.dto.FiltroDTO;
import br.com.ambevtech.weather.service.CidadeService;
import io.swagger.annotations.ApiParam;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cidade")
public class CidadeResource {

    private final CidadeService service;

    @Autowired
    public CidadeResource(CidadeService service) {
        this.service = service;
    }

    @PostMapping("/listar")
    public ResponseEntity<?> listarCidades(@RequestBody FiltroDTO<String> filtro) throws ServiceException {
        return ResponseEntity.ok(service.listarCidades(filtro));
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCidade(@Valid @RequestBody CidadeDTO dto, BindingResult bindingResult) throws ServiceException {
        return ResponseEntity.ok(service.cadastrarCidade(dto));
    }

    @GetMapping("/previsao/{id}")
    public ResponseEntity<?> consultarPrevisao(@PathVariable("id") Integer id) throws ServiceException {
        return ResponseEntity.ok(service.consultarPrevisao(id));
    }

}
