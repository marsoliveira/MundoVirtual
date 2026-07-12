package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.interfaces.Voador;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Draconis extends Criatura implements Voador {

    public Draconis(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Draconis", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.sePodeTreinar()) {
            this.ganharExperiencia(35);
            this.consumirEnergia(25);
            this.consumirSaciedade(15);
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.sePodeExplorar()) {
            this.ganharExperiencia(20);
            this.consumirEnergia(15);
            this.consumirSaciedade(10);
            this.ganharFelicidade(5);
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.sePodeBrincar()) {
            this.ganharFelicidade(20);
            this.consumirEnergia(10);
            this.consumirSaciedade(5);
            this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

	@Override
    public Set<TipoAlimento> getAlimentosCompativeis() {
        return Set.of(TipoAlimento.FRUTAS, TipoAlimento.CARNE, TipoAlimento.BANQUETE_REAL);
    }

    @Override
    public void descansar() {
        if (this.sePodeDescansar()) {
            this.ganharEnergia(30);
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.sePodeParticiparDesafio()) {
            this.ganharExperiencia(60);
            this.consumirEnergia(30);
            this.consumirSaciedade(20);
            this.ganharFelicidade(10);
            this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    protected void evoluir() {
        this.ganharEnergia(2);
        this.ultimaAtividade = Atividade.EVOLUIR;
    }

    @Override
    public void realizarVoo() {
        this.felicidade += 10;
        System.out.println(this.nome + " voou e sua felicidade agora é " + this.felicidade);
    }

	@Override
	public void executarHabilidadeEspecial() {
		this.realizarVoo();
	}

    @Override
    protected int desgasteEnergia() {
        return 5;
    }

    @Override
    protected int desgasteSaciedade() {
        return 4;
    }

    @Override
    protected int desgasteFelicidade() {
        return 2;
    }

}
