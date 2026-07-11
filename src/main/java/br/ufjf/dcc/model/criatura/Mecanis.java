package br.ufjf.dcc.model.criatura;

import br.ufjf.dcc.interfaces.Mecanico;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Mecanis extends Criatura implements Mecanico {

    public Mecanis(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Mecanis", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.sePodeTreinar()) {
            this.experiencia += 40;
            this.energia -= 15;
            this.saciedade -= 20;
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.sePodeExplorar()) {
            this.experiencia += 25;
            this.energia -= 10;
            this.saciedade -= 15;
            this.felicidade -= 5;
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.sePodeBrincar()) {
            this.felicidade += 10;
            this.energia -= 5;
            this.saciedade -= 10;
            this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

    @Override
    public boolean aceitaAlimento(TipoAlimento tipoAlimento) {
        return tipoAlimento == TipoAlimento.COGUMELOS || tipoAlimento == TipoAlimento.CRISTAIS_ENERGETICOS || tipoAlimento == TipoAlimento.BANQUETE_REAL;
    }

    @Override
    public void descansar() {
        if (this.sePodeDescansar()) {
            this.energia += 15;
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.sePodeParticiparDesafio()) {
            this.experiencia += 50;
            this.energia -= 20;
            this.saciedade -= 25;
            this.felicidade -= 10;
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    public void evoluir() {
        if (this.sePodeEvoluir()) {
            this.energia += 3;
            this.ultimaAtividade = Atividade.EVOLUIR;
        }
    }

    @Override
    public void realizarManutencao() {
        this.saude += 10;
        System.out.println(this.nome + " realizou manutenção e sua saúde agora é " + this.saude);
    }

}
