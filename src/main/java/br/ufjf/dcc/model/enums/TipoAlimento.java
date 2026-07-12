package br.ufjf.dcc.model.enums;

public enum TipoAlimento {

    FRUTAS("Frutas", 20),
    CARNE("Carne", 35),
    FOTONS("Fotons", 20),
    NECTAR_LUMINOSO("Nectar Luminoso", 35),
    COGUMELOS("Cogumelos", 20),
    CRISTAIS_ENERGETICOS("Cristais Energeticos", 35),
    BANQUETE_REAL("Banquete Real", 50);

    private final String descricao;
    private final int saciedade;

    TipoAlimento(String descricao, int saciedade) {
        this.descricao = descricao;
        this.saciedade = saciedade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getSaciedade() {
        return saciedade;
    }

    public static TipoAlimento obterPorDescricao(String descricao) {

        for (TipoAlimento tipo : TipoAlimento.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo de alimento inválido: " + descricao);
    }
}
