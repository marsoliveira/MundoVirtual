package br.ufjf.dcc.model.enums;

public enum EstadoEnergia {

    CANSADA("Cansada"),
    DISPOSTA("Disposta");

    private final String descricao;

    EstadoEnergia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
