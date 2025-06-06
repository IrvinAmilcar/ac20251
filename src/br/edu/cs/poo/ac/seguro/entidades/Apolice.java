package br.edu.cs.poo.ac.seguro.entidades;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Apolice implements Registro {
    //Atributos:
    private String numero;
    private Veiculo veiculo;
    private BigDecimal valorFranquia;
    private BigDecimal valorPremio;
    private BigDecimal valorMaximoSegurado;
    private LocalDate dataInicioVigencia; //Adicionado a partir da parte 2 de mediators

    @Override
    public String getIdUnico() {
        return this.getNumero();
    }

    //Os métodos especiais serão inicializados elo lombok automaticamente!
}