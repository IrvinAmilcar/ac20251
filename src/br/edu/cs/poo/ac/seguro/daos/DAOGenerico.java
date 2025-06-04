package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.Registro;

import java.io.Serializable;

public abstract class DAOGenerico<T extends Registro> {

    private CadastroObjetos cadastro;

    public abstract Class<T> getClasseEntidade();

    public DAOGenerico() {
        this.cadastro = new CadastroObjetos(getClasseEntidade());
    }

    @SuppressWarnings("unchecked")
    public T buscar(String idUnico) {
        return (T) cadastro.buscar(idUnico);
    }

    public boolean incluir(T registro) {
        if (buscar(registro.getIdUnico()) != null) {
            return false;
        } else {
            cadastro.incluir(registro, registro.getIdUnico());
            return true;
        }
    }

    public boolean alterar(T registro) {
        if (buscar(registro.getIdUnico()) == null) {
            return false;
        } else {
            cadastro.alterar(registro, registro.getIdUnico());
            return true;
        }
    }

    public boolean excluir(String idUnico) {
        if (buscar(idUnico) == null) {
            return false;
        } else {
            cadastro.excluir(idUnico);
            return true;
        }
    }

    public Registro[] buscarTodos() {
        Serializable[] objetosSerializaveis = cadastro.buscarTodos();
        if (objetosSerializaveis == null || objetosSerializaveis.length == 0) {
            return new Registro[0]; // Return empty array instead of null
        }
        Registro[] registros = new Registro[objetosSerializaveis.length];
        for (int i = 0; i < objetosSerializaveis.length; i++) {
            registros[i] = (Registro) objetosSerializaveis[i];
        }
        return registros;
    }
}
