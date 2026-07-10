package br.ufjf.dcc.model.enums;

public enum EstadoFelicidade {

    TRISTE("Triste"),
    FELIZ("Feliz");

    private final String descricao;

    EstadoFelicidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
