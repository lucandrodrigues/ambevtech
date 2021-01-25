package br.com.ambevtech.weather.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cidade")
public class Cidade extends AbstractEntity implements Serializable {

    @NotBlank
    private String nome;

    private BigDecimal longitude;

    private BigDecimal latitude;

}
