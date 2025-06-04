package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Registro;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;

public class SinistroDAO extends DAOGenerico<Sinistro> {

    @Override
    public Class<Sinistro> getClasseEntidade() {
        return Sinistro.class;
    }

    // buscar(String numero) is inherited and returns Sinistro
    // incluir(Sinistro sinistro) is inherited
    // alterar(Sinistro sinistro) is inherited
    // excluir(String numero) is inherited

    @Override
    public Sinistro[] buscarTodos() {
        Registro[] registros = super.buscarTodos();
        if (registros == null || registros.length == 0) {
            return new Sinistro[0];
        }
        Sinistro[] sinistros = new Sinistro[registros.length];
        for (int i = 0; i < registros.length; i++) {
            sinistros[i] = (Sinistro) registros[i];
        }
        return sinistros;
    }
}