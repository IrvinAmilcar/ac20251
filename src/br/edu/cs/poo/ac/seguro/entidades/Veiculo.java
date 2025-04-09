package br.edu.cs.poo.ac.seguro.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Veiculo {

    //Atributos:
    private String placa;
    private int ano;
    private SeguradoEmpresa prorpietarioEmpresa;
    private SeguradoPessoa proprietarioPessoa;
    private CategoriaVeiculo categoria;

}
