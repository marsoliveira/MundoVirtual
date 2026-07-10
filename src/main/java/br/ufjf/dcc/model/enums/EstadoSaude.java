package br.ufjf.dcc.model.enums;

public enum EstadoSaude {

    SAUDAVEL("Saudável"),
    ATENCAO("Atenção"),
    DOENTE("Doente"),
    MORTA("Morta");

    private final String descricao;

    EstadoSaude(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
