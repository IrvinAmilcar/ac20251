package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;

import java.io.Serializable;

public class SinistroDAO extends DAOGenerico{

    public SinistroDAO(){
        cadastro = new CadastroObjetos(Sinistro.class);
    }

    public Sinistro buscar(String numero) {
        return (Sinistro)cadastro.buscar(numero);
    }

    public boolean incluir(Sinistro segurado) {
        if (buscar(segurado.getNumero()) != null) {
            return false;
        } else {
            cadastro.incluir(segurado, segurado.getNumero());
            return true;
        }
    }

    public boolean alterar(Sinistro segurado) {
        if (buscar(segurado.getNumero()) == null) {
            return false;
        } else {
            cadastro.alterar(segurado, segurado.getNumero());
            return true;
        }
    }

    public boolean excluir(String numero) {
        if (buscar(numero) == null) {
            return false;
        } else {
            cadastro.excluir(numero);
            return true;
        }
    }

    public Sinistro[] buscarTodos() {
        Serializable[] objetosSerializaveis = cadastro.buscarTodos();

        if (objetosSerializaveis == null) {
            return null;
        }

        Sinistro[] sinistros = new Sinistro[objetosSerializaveis.length];
        for (int i = 0; i < objetosSerializaveis.length; i++) {
            sinistros[i] = (Sinistro) objetosSerializaveis[i];
        }

        return sinistros;
    }
}
