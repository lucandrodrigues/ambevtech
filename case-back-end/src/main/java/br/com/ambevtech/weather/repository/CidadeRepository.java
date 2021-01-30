package br.com.ambevtech.weather.repository;

import br.com.ambevtech.weather.dto.CidadeDTO;
import br.com.ambevtech.weather.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query("SELECT new br.com.ambevtech.weather.dto.CidadeDTO(" +
            "c.id, c.nome, c.latitude, c.longitude) " +
            "FROM Cidade c " +
            "WHERE (:nome is null or c.nome like CONCAT('%', :nome, '%')) " +
            "ORDER BY c.nome")
    Page<CidadeDTO> listarCidades(String nome, Pageable pageable);

    @Query("SELECT new br.com.ambevtech.weather.dto.CidadeDTO(" +
            "c.id, c.nome, c.latitude, c.longitude) " +
            "FROM Cidade c " +
            "WHERE c.nome = :nome")
    CidadeDTO findByNome(String nome);
}
