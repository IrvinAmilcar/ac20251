package br.edu.cs.poo.ac.seguro.entidades;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(of = "placa")
public class Veiculo implements Registro {

    //Atributos:
    private String placa;
    private int ano;
    private Segurado proprietario; // Alterado de proprietarioEmpresa e proprietarioPessoa
    private CategoriaVeiculo categoria;

    public Veiculo(String placa, int ano, Segurado proprietario, CategoriaVeiculo categoria) {
        this.placa = placa;
        this.ano = ano;
        this.proprietario = proprietario;
        this.categoria = categoria;
    }

    @Override
    public String getIdUnico() {
        return this.getPlaca();
    }
}