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
            this.ganharExperiencia(40);
            this.consumirEnergia(15);
            this.consumirSaciedade(20);
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.sePodeExplorar()) {
            this.ganharExperiencia(25);
            this.consumirEnergia(10);
            this.consumirSaciedade(15);
            this.consumirFelicidade(5);
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.sePodeBrincar()) {
            this.ganharFelicidade(10);
            this.consumirEnergia(5);
            this.consumirSaciedade(10);
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
            this.ganharEnergia(15);
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.sePodeParticiparDesafio()) {
            this.ganharExperiencia(50);
            this.consumirEnergia(20);
            this.consumirSaciedade(25);
            this.consumirFelicidade(10);
            this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    protected void evoluir() {
        this.ganharEnergia(3);
        this.ultimaAtividade = Atividade.EVOLUIR;
    }

    @Override
    public void realizarManutencao() {
        this.saude += 10;
        System.out.println(this.nome + " realizou manutenção e sua saúde agora é " + this.saude);
    }

    @Override
    protected int desgasteEnergia() {
        return 2;
    }

    @Override
    protected int desgasteSaciedade() {
        return 5;
    }

    @Override
    protected int desgasteFelicidade() {
        return 1;
    }

}
