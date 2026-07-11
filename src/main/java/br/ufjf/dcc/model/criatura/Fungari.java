package br.ufjf.dcc.model.criatura;

import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Fungari extends Criatura {

    public Fungari(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Fungari", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.sePodeTreinar()) {
            this.experiencia += 20;
            this.energia -= 10;
            this.saciedade -= 10;
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.sePodeExplorar()) {
            this.experiencia += 10;
            this.energia -= 5;
            this.saciedade -= 5;
            this.felicidade += 5;
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.sePodeBrincar()) {
            this.felicidade += 15;
            this.energia -= 5;
            this.saciedade -= 5;
            this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

    @Override
    public boolean aceitaAlimento(TipoAlimento tipoAlimento) {
        return tipoAlimento == TipoAlimento.FRUTA || tipoAlimento == TipoAlimento.COGUMELOS || tipoAlimento == TipoAlimento.BANQUETE_REAL;
    }

    @Override
    public void descansar() {
        if (this.sePodeDescansar()) {
            this.energia += 20;
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.sePodeParticiparDesafio()) {
            this.experiencia += 40;
            this.energia -= 15;
            this.saciedade -= 15;
            this.felicidade += 5;
			this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

	@Override
    public void evoluir() {
        if (this.sePodeEvoluir()) {
            this.saciedade += 2;
            this.ultimaAtividade = Atividade.EVOLUIR;
        }
    }

}
