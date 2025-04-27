package br.edu.cs.poo.ac.seguro.testes;

//Mais uma classe dada pelo professor (3 a ser implementada)

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public abstract class TesteAbstrato {
    protected CadastroObjetos cadastro;

    protected abstract Class getClasse();

    protected TesteAbstrato() {
        cadastro = new CadastroObjetos(getClasse());
    }

    @BeforeEach
    public void setUp() {
        String sep = File.separator;
        FileUtils.limparDiretorio("." + sep + getClasse().getSimpleName());
    }
}
