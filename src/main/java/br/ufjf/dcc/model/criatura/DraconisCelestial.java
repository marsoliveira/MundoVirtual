package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.interfaces.Autodidata;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class DraconisCelestial extends Draconis implements Autodidata {

	private static final int GANHO_EXP_ESTUDAR = 30;

    public DraconisCelestial(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, idade, nivel, experiencia, energia, saciedade, felicidade);
        this.especie = "Draconis Celestial";
    }

	@Override
    public Set<TipoAlimento> getAlimentosCompativeis() {
        return Set.of(TipoAlimento.FOTONS, TipoAlimento.CARNE, TipoAlimento.BANQUETE_REAL);
    }

    @Override
    public void estudar() {
        this.experiencia += GANHO_EXP_ESTUDAR;
        System.out.println(this.nome + " estudou e sua experiência agora é " + this.experiencia);
    }

	@Override
	public void executarHabilidadeEspecial() {
		this.realizarVoo();
		this.estudar();
	}

}
