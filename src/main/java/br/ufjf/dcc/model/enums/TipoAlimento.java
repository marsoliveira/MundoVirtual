package br.ufjf.dcc.model.enums;

public enum TipoAlimento {

    FRUTA("Fruta"),
    CARNE("Carne"),
    FOTONS("Fotons"),
    NECTAR_LUMINOSO("Nectar Luminoso"),
    COGUMELOS("Cogumelos"),
    CRISTAIS_ENERGETICOS("Cristais Energeticos"),
    BANQUETE_REAL("Feliz");

    private final String descricao;

    TipoAlimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
