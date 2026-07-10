package br.ufjf.dcc.model.enums;

public enum EstadoSaciedade {

    FAMINTA("Faminta"),
    SATISFEITA("Satisfeita");

    private final String descricao;

    EstadoSaciedade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
	
}
