package br.edu.cs.poo.ac.seguro.entidades;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Apolice {
    //Atributos:
    private Veiculo veiculo;
    private BigDecimal valorFranquia;
    private BigDecimal valorPremio;
    private BigDecimal valorMaximoSegurado;

    //Os métodos especiais serão inicializados elo lombok automaticamente!
}
