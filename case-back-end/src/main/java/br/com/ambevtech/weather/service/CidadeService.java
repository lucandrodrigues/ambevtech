package br.com.ambevtech.weather.service;

import br.com.ambevtech.weather.config.CacheNames;
import br.com.ambevtech.weather.dto.CidadeDTO;
import br.com.ambevtech.weather.dto.FiltroDTO;
import br.com.ambevtech.weather.dto.previsao.PrevisaoDTO;
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
import org.springframework.web.client.HttpClientErrorException;

@Service
public class CidadeService {
    private final CidadeRepository repository;
    private final OpenWeatherApiService openWeatherApiService;

    @Autowired
    public CidadeService(CidadeRepository repository, OpenWeatherApiService openWeatherApiService) {
        this.repository = repository;
        this.openWeatherApiService = openWeatherApiService;
    }

    /**
     * @author Lucas Andrade
     * @param 'FiltroDTO<CidadeDTO>' - filtro genérico para busca de cidades
     * @return 'Page<CidadeDTO>' - Pagina de dto de cidades
     * @throws ServiceException
     * @method Método responsável por listar as cidades cadastradas
     */
    public Page<CidadeDTO> listarCidades(FiltroDTO<CidadeDTO> filtro) throws ServiceException {
        if (ObjectUtils.isEmpty(filtro)) {
            throw new ServiceException(EnumErrorException.PARAMETROS_INVALIDOS);
        }

        try {
            return repository.listarCidades(filtro.getObj().getNome(), PageRequest.of(filtro.getPage(), filtro.getSize()));
        } catch (Exception e) {
            throw new ServiceException(EnumErrorException.ERRO_INTERNO, new Object[]{"Falha ao buscar cidades no banco de dados."});
        }
    }

    /**
     * @author Lucas Andrade
     * @param 'String' - Nome da cidade a ser cadastrada
     * @return 'CidadeDTO' -  Dto da cidade
     * @throws ServiceException
     * @method Método responsável por cadastrar uma cidade válida
     */
    public CidadeDTO cadastrarCidade(String nome) throws ServiceException {
        CidadeDTO cidadeDTO = validarCidadeCadastrada(nome);
        try {
            cidadeDTO = openWeatherApiService.buscarCidade(nome);
            salvar(cidadeDTO);
            return cidadeDTO;
        } catch (HttpClientErrorException e) {
            throw new ServiceException(EnumErrorException.NAO_LOCALIZADO, new Object[]{"Cidade não localizada"});
        } catch (Exception e) {
            throw new ServiceException(EnumErrorException.ERRO_INTERNO, new Object[]{e.getMessage()});
        }
    }

    /**
     * @author Lucas Andrade
     * @param 'CidadeDTO' - Dto da cidade para conversão em entidade
     * @method Método responsável por persistir se a cidade no banco de dados
     */
    private void salvar(CidadeDTO cidadeDTO) {
        Cidade cidade = new Cidade();
        BeanUtils.copyProperties(cidadeDTO, cidade);
        cidade = repository.save(cidade);
        BeanUtils.copyProperties(cidade, cidadeDTO);
    }

    /**
     * @author Lucas Andrade
     * @param 'String' - Nome da cidade a ser validada
     * @return 'CidadeDTO' -  Dto da cidade
     * @throws ServiceException
     * @method Método responsável por verificar se a cidade já está cadastrada
     */
    private CidadeDTO validarCidadeCadastrada(String nome) throws ServiceException {
        CidadeDTO dto = repository.findByNome(nome);
        if (!ObjectUtils.isEmpty(dto)) {
            throw new ServiceException(EnumErrorException.DUPLICADO, new Object[]{"Esta cidade já está cadastrada."});
        }
        return dto;
    }

    /**
     * @author Lucas Andrade
     * @param 'Integer' - Identificador da cidade
     * @return 'PrevisaoDTO' -  Dto da previsão da cidade
     * @throws ServiceException
     * @method Método responsável por consultar a previsão do tempo de uma cidade
     */
    @Cacheable(value = CacheNames.cachePrevisao, key = "{#id}")
    public PrevisaoDTO consultarPrevisao(Integer id) throws ServiceException {
        Cidade cidade = repository.findById(id)
                .orElseThrow(() -> new ServiceException(EnumErrorException.NAO_LOCALIZADO, new Object[]{"Cidade não localizada"}));

        return openWeatherApiService.buscarPrevisao(cidade);
    }
}
