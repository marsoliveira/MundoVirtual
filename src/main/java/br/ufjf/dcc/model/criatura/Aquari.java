package br.ufjf.dcc.model.criatura;

import br.ufjf.dcc.interfaces.Aquatico;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Aquari extends Criatura implements Aquatico {

    public Aquari(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Aquari", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.sePodeTreinar()) {
            this.experiencia += 25;
            this.energia -= 15;
            this.saciedade -= -10;
			this.ultimaAtividade = Atividade.TREINAR;
        }
    }

	@Override
	public void explorar() {
        if (this.sePodeExplorar()) {
            this.experiencia += 15;
            this.energia -= 10;
            this.saciedade -= 10;
            this.felicidade += 5;
			this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

	@Override
	public void brincar() {
        if (this.sePodeBrincar()) {
            this.energia -= 10;
            this.saciedade -= 5;
            this.felicidade += 20;
			this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

	@Override
	public boolean aceitaAlimento(TipoAlimento tipoAlimento) {
		return tipoAlimento == TipoAlimento.NECTAR_LUMINOSO || tipoAlimento == TipoAlimento.CRISTAIS_ENERGETICOS;
    }

	@Override
	public void descansar() {
        if (this.sePodeDescansar()) {
            this.energia -= 10;
            this.saciedade -= 5;
            this.felicidade += 20;
			this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

	@Override
	public void participarDesafio() {
        if (this.sePodeParticiparDesafio()) {
            this.experiencia += 45;
            this.energia -= 20;
            this.saciedade -= 20;
            this.felicidade += 10;
			this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

	@Override
	public void evoluir() {
        if (this.sePodeEvoluir()) {
            this.energia += 1;
            this.felicidade += 1;
			this.ultimaAtividade = Atividade.EVOLUIR;
        }
    }

	@Override
	public void mergulhar() {
		this.felicidade += 10;
		System.out.println(this.nome + " mergulhou e sua felicidade agora é " + this.felicidade);
	}

}
