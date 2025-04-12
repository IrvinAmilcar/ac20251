package br.edu.cs.poo.ac.seguro.entidades;

public enum TipoSinistro {

    COLISAO(1, "Colisão"),
    INCENDIO(2, "Incêncio"),
    FURTO(3, "Furto"),
    ENCHENTE(4, "Enchente"),
    DEPREDACAO(5, "Depredação");

    private int codigo;
    private String nome;

    private TipoSinistro(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public static TipoSinistro getTipoSinistro(int codigo) {
        if (codigo == 1) {
            return TipoSinistro.COLISAO;
        } else if (codigo == 2) {
            return TipoSinistro.INCENDIO;
        } else if (codigo == 3) {
            return TipoSinistro.FURTO;
        } else if (codigo == 4) {
            return TipoSinistro.ENCHENTE;
        } else if (codigo == 5) {
            return TipoSinistro.DEPREDACAO;
        } else {
            return null;
        }
    }
}
