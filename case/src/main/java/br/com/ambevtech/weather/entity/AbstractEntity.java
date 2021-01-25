package br.com.ambevtech.weather.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    private void setDataCriacao() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    private void setDataAtualizacao() {
        this.dataAtualizacao = LocalDateTime.now();
    }

}
