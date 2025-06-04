package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public abstract class Segurado implements Registro {

    //Atributos:
    private String nome;
    private Endereco endereco;
    private LocalDate dataCriacao;
    private BigDecimal bonus;

    //Métodos especiais:
    public Segurado(String nome, Endereco endereco, LocalDate dataCriacao, BigDecimal bonus) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCriacao = dataCriacao;
        this.bonus = bonus;
    }

    public Segurado() {
        this.nome = "";
        this.endereco = null;
        this.dataCriacao = LocalDate.now();
        this.bonus = BigDecimal.ZERO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    protected LocalDate getDataCriacao() {
        return dataCriacao;
    }

    protected void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    //métodos personalizados:
    public int getIdade(){
        return Period.between(dataCriacao, LocalDate.now()).getYears();
    }

    public void creditarBonus(BigDecimal valor){
        bonus = bonus.add(valor);
    }

    public void debitarBonus(BigDecimal valor){
        bonus = bonus.subtract(valor);
    }

    public abstract boolean isEmpresa();

    // getIdUnico will be implemented by subclasses SeguradoPessoa and SeguradoEmpresa
}