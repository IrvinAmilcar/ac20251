package br.edu.cs.poo.ac.seguro.entidades;

import static br.edu.cs.poo.ac.seguro.entidades.PrecoAnosCategoria.*;

public enum CategoriaVeiculo {

    BASICO(1, "Veículo econômico", PA_BASICO),
    INTERMEDIARIO(2, "Veículo de categoria média", PA_INTERMEDIARIO),
    LUXO(4, "Veículo exclusivo", PA_LUXO),
    ESPORTIVO(5, "Veículo esportivo", PA_ESPORTIVO);

    //Atributos:
    private int codigo;
    private String nome;
    private PrecoAno[] precoAnos;

    //Métodos especiais:
    private CategoriaVeiculo(int codigo, String nome, PrecoAno[] precoAnos){

        this.codigo = codigo;
        this.nome = nome;
        this.precoAnos = precoAnos;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public PrecoAno[] getPrecoAnos() {
        return precoAnos;
    }
}
