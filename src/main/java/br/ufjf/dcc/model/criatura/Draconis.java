package br.ufjf.dcc.model.criatura;

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
            this.experiencia += 35;
            this.energia -= 25;
            this.saciedade -= 15;
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.sePodeExplorar()) {
            this.experiencia += 20;
            this.energia -= 15;
            this.saciedade -= 10;
            this.felicidade += 5;
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.sePodeBrincar()) {
            this.felicidade += 20;
            this.energia -= 10;
            this.saciedade -= 5;
            this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

    @Override
    public boolean aceitaAlimento(TipoAlimento tipoAlimento) {
        return tipoAlimento == TipoAlimento.FRUTA || tipoAlimento == TipoAlimento.CARNE || tipoAlimento == TipoAlimento.BANQUETE_REAL;
    }

    @Override
    public void descansar() {
        if (this.sePodeDescansar()) {
            this.energia += 30;
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.sePodeParticiparDesafio()) {
            this.experiencia += 60;
            this.energia -= 30;
            this.saciedade -= 20;
            this.felicidade += 10;
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    public void evoluir() {
        if (this.sePodeEvoluir()) {
            this.energia += 2;
            this.ultimaAtividade = Atividade.EVOLUIR;
        }
    }

    @Override
    public void realizarVoo() {
        this.felicidade += 10;
        System.out.println(this.nome + " voou e sua felicidade agora é " + this.felicidade);
    }

}
