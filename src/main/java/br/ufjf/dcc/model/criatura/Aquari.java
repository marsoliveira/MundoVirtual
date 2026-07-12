package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.interfaces.Aquatico;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Aquari extends Criatura implements Aquatico {

    public Aquari(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Aquari", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.podeTreinar()) {
            this.ganharExperiencia(25);
            this.consumirEnergia(15);
            this.consumirSaciedade(10);
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.podeExplorar()) {
            this.ganharExperiencia(15);
            this.consumirEnergia(10);
            this.consumirSaciedade(10);
            this.ganharFelicidade(5);
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.podeBrincar()) {
            this.ganharFelicidade(20);
            this.consumirEnergia(10);
            this.consumirSaciedade(5);
            this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

    @Override
    public Set<TipoAlimento> getAlimentosCompativeis() {
        return Set.of(TipoAlimento.NECTAR_LUMINOSO, TipoAlimento.CRISTAIS_ENERGETICOS, TipoAlimento.BANQUETE_REAL);
    }

    @Override
    public void descansar() {
        if (this.podeDescansar()) {
            this.ganharEnergia(25);
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.podeParticiparDesafio()) {
            this.ganharExperiencia(45);
            this.consumirEnergia(20);
            this.consumirSaciedade(20);
            this.ganharFelicidade(10);
            this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    protected void evoluir() {
        this.ganharEnergia(1);
        //this.ganharFelicidade(1);
        this.felicidade += 1;
        this.ultimaAtividade = Atividade.EVOLUIR;
    }

    @Override
    public void mergulhar() {
        this.felicidade += 10;
        System.out.println(this.nome + " mergulhou e sua felicidade agora é " + this.felicidade);
    }

	@Override
	public void executarHabilidadeEspecial() {
		this.mergulhar();
	}

    @Override
    protected int desgasteEnergia() {
        return 4;
    }

    @Override
    protected int desgasteSaciedade() {
        return 3;
    }

    @Override
    protected int desgasteFelicidade() {
        return 2;
    }

}
