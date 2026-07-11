package br.ufjf.dcc.model.criatura;

import br.ufjf.dcc.interfaces.Luminescente;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Lumini extends Criatura implements Luminescente {

    public Lumini(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Lumini", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.sePodeTreinar()) {
            this.ganharExperiencia(25);
            this.energia -= 20;
            this.saciedade -= 10;
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.sePodeExplorar()) {
            this.ganharExperiencia(15);
            this.energia -= 20;
            this.saciedade -= 5;
            this.felicidade += 10;
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.sePodeBrincar()) {
            this.felicidade += 35;
            this.energia -= 5;
            this.saciedade -= 5;
            this.ultimaAtividade = Atividade.BRINCAR;
        }
    }

    @Override
    public boolean aceitaAlimento(TipoAlimento tipoAlimento) {
        return tipoAlimento == TipoAlimento.NECTAR_LUMINOSO || tipoAlimento == TipoAlimento.FOTONS || tipoAlimento == TipoAlimento.BANQUETE_REAL;
    }

    @Override
    public void descansar() {
        if (this.sePodeDescansar()) {
            this.energia += 40;
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.sePodeParticiparDesafio()) {
            this.ganharExperiencia(45);
            this.energia -= 35;
            this.saciedade -= 15;
            this.felicidade += 15;
            this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    protected void evoluir() {
        this.energia += 2;
        this.ultimaAtividade = Atividade.EVOLUIR;
    }

    @Override
    public void emitirLuz() {
        this.felicidade += 10;
        System.out.println(this.nome + " emitiu luz e sua felicidade agora é " + this.felicidade);
    }

}
