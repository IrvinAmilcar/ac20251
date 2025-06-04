package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class VeiculoDAO extends DAOGenerico<Veiculo> {

    @Override
    public Class<Veiculo> getClasseEntidade() {
        return Veiculo.class;
    }

    // buscar(String placa) is inherited and returns Veiculo
    // incluir(Veiculo veiculo) is inherited
    // alterar(Veiculo veiculo) is inherited
    // excluir(String placa) is inherited
}