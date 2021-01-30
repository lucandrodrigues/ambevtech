package br.com.ambevtech.weather.service;

import br.com.ambevtech.weather.config.CacheNames;
import br.com.ambevtech.weather.dto.CidadeDTO;
import br.com.ambevtech.weather.dto.FiltroDTO;
import br.com.ambevtech.weather.dto.PrevisaoDTO;
import br.com.ambevtech.weather.entity.Cidade;
import br.com.ambevtech.weather.exception.EnumErrorException;
import br.com.ambevtech.weather.exception.ServiceException;
import br.com.ambevtech.weather.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CidadeService {
    private final CidadeRepository repository;
    private final OpenWeatherApiService openWeatherApiService;

    @Autowired
    public CidadeService(CidadeRepository repository, OpenWeatherApiService openWeatherApiService) {
        this.repository = repository;
        this.openWeatherApiService = openWeatherApiService;
    }

    public Page<CidadeDTO> listarCidades(FiltroDTO<CidadeDTO> filtro) throws ServiceException {
        if (ObjectUtils.isEmpty(filtro)) {
            throw new ServiceException(EnumErrorException.PARAMETROS_INVALIDOS);
        }

        Page<CidadeDTO> cidades = null;
        try {
            cidades = repository.listarCidades(filtro.getObj().getNome(), PageRequest.of(filtro.getPage(), filtro.getSize()));
        } catch (Exception e) {
            throw new ServiceException(EnumErrorException.ERRO_INTERNO, new Object[]{"Falha ao buscar cidades no banco de dados."});
        }
        return cidades;
    }

    public CidadeDTO cadastrarCidade(CidadeDTO dto) throws ServiceException {
        CidadeDTO cidadeDTO = repository.findByNome(dto.getNome());;
        if (!ObjectUtils.isEmpty(cidadeDTO)) {
            throw new ServiceException(EnumErrorException.DUPLICADO, new Object[]{"Esta cidade já está cadastrada."});
        }

        try {
            cidadeDTO = openWeatherApiService.buscarCidade(dto.getNome());
            Cidade cidade = new Cidade();
            BeanUtils.copyProperties(cidadeDTO, cidade);
            cidade = repository.save(cidade);
            BeanUtils.copyProperties(cidade, cidadeDTO);
        } catch (Exception e) {
            throw new ServiceException(EnumErrorException.ERRO_INTERNO, new Object[]{"Falha cadastrar cidade."});
        }

        return cidadeDTO;
    }

    @Cacheable(value = CacheNames.cachePrevisao, key = "{#id}")
    public PrevisaoDTO consultarPrevisao(Integer id) throws ServiceException {
        Cidade cidade = repository.findById(id)
                .orElseThrow(() -> new ServiceException(EnumErrorException.NAO_LOCALIZADO, new Object[]{"Falha ao localizar cidade."}));

        return openWeatherApiService.buscarPrevisao(cidade);
    }
}
