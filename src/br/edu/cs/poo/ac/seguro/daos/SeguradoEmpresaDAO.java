package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaDAO extends SeguradoDAO<SeguradoEmpresa> {
    @Override
    public Class getClasseEntidade() {
        return SeguradoEmpresa.class;
    }

    public SeguradoEmpresa buscar(String cnpj) {
        return super.buscar(cnpj);
    }
}