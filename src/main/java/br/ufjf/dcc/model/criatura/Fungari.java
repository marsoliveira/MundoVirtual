package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Fungari extends Criatura {

    public Fungari(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Fungari", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.podeTreinar()) {
            this.ganharExperiencia(20);
            this.consumirEnergia(10);
            this.consumirSaciedade(10);
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.podeExplorar()) {
            this.ganharExperiencia(10);
            this.consumirEnergia(5);
            this.consumirSaciedade(5);
            this.ganharFelicidade(5);
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.podeBrincar()) {
            this.ganharFelicidade(15);
            this.consumirEnergia(5);
            this.consumirSaciedade(5);
            this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

	@Override
    public Set<TipoAlimento> getAlimentosCompativeis() {
        return Set.of(TipoAlimento.FRUTAS, TipoAlimento.COGUMELOS, TipoAlimento.BANQUETE_REAL);
    }

    @Override
    public void descansar() {
        if (this.podeDescansar()) {
            this.ganharEnergia(20);
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.podeParticiparDesafio()) {
            this.ganharExperiencia(40);
            this.consumirEnergia(15);
            this.consumirSaciedade(15);
            this.ganharFelicidade(5);
            this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    protected void evoluir() {
        this.saciedade += 2;
        this.ultimaAtividade = Atividade.EVOLUIR;
    }

	@Override
	public void executarHabilidadeEspecial() {
		System.out.println(this.especie + " não possuem habilidade especial!");
	}

	@Override
    protected int desgasteEnergia() {
        return 3;
    }

    @Override
    protected int desgasteSaciedade() {
        return 2;
    }

    @Override
    protected int desgasteFelicidade() {
        return 2;
    }

}
