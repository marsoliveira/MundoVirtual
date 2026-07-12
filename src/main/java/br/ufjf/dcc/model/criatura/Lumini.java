package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.interfaces.Luminescente;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Lumini extends Criatura implements Luminescente {

    public Lumini(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Lumini", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.podeTreinar()) {
            this.ganharExperiencia(25);
            this.consumirEnergia(20);
            this.consumirSaciedade(10);
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.podeExplorar()) {
            this.ganharExperiencia(15);
            this.consumirEnergia(20);
            this.consumirSaciedade(5);
            this.ganharFelicidade(10);
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.podeBrincar()) {
            this.ganharFelicidade(35);
            this.consumirEnergia(5);
            this.consumirSaciedade(5);
            this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

	@Override
    public Set<TipoAlimento> getAlimentosCompativeis() {
        return Set.of(TipoAlimento.NECTAR_LUMINOSO, TipoAlimento.FOTONS, TipoAlimento.BANQUETE_REAL);
    }

    @Override
    public void descansar() {
        if (this.podeDescansar()) {
            this.ganharEnergia(40);
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.podeParticiparDesafio()) {
            this.ganharExperiencia(45);
            this.consumirEnergia(35);
            this.consumirSaciedade(15);
            this.ganharFelicidade(15);
            this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    protected void evoluir() {
        //this.ganharFelicidade(2);
        this.felicidade += 2;
        this.ultimaAtividade = Atividade.EVOLUIR;
    }

    @Override
    public void emitirLuz() {
        this.felicidade += 10;
        System.out.println(this.nome + " emitiu luz e sua felicidade agora é " + this.felicidade);
    }

	@Override
	public void executarHabilidadeEspecial() {
		this.emitirLuz();
	}

    @Override
    protected int desgasteEnergia() {
        return 8;
    }

    @Override
    protected int desgasteSaciedade() {
        return 2;
    }

    @Override
    protected int desgasteFelicidade() {
        return 3;
    }

}
