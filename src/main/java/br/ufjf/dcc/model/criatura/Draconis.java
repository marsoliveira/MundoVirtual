package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.interfaces.Voador;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Draconis extends Criatura implements Voador {

    private static final int GANHO_EXP_TREINAR = 35;
    private static final int CONSUMO_ENERGIA_TREINAR = 25;
    private static final int CONSUMO_SACIEDADE_TREINAR = 15;

    private static final int GANHO_EXP_EXPLORAR = 20;
    private static final int CONSUMO_ENERGIA_EXPLORAR = 15;
    private static final int CONSUMO_SACIEDADE_EXPLORAR = 10;
    private static final int GANHO_FELICIDADE_EXPLORAR = 5;

    private static final int GANHO_FELICIDADE_BRINCAR = 20;
    private static final int CONSUMO_ENERGIA_BRINCAR = 10;
    private static final int CONSUMO_SACIEDADE_BRINCAR = 5;

    private static final int GANHO_ENERGIA_DESCANSAR = 30;

    private static final int GANHO_EXP_DESAFIAR = 60;
    private static final int CONSUMO_ENERGIA_DESAFIAR = 30;
    private static final int CONSUMO_SACIEDADE_DESAFIAR = 20;
    private static final int GANHO_FELICIDADE_DESAFIAR = 10;

    private static final int GANHO_ENERGIA_EVOLUIR = 2;

    private static final int GANHO_FELICIDADE_VOAR = 10;

    private static final int DESGASTE_ENERGIA = 5;
    private static final int DESGASTE_SACIEDADE = 4;
    private static final int DESGASTE_FELICIDADE = 2;

    public Draconis(String nome, int idade) {
        super(nome, "Draconis", idade);
    }

    public Draconis(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Draconis", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public Set<TipoAlimento> getAlimentosCompativeis() {
        return Set.of(TipoAlimento.FRUTAS, TipoAlimento.CARNE, TipoAlimento.BANQUETE_REAL);
    }

    @Override
    public void treinar() {
        if (!this.podeTreinar()) {
            throw new IllegalStateException("A criatura não pode treinar agora.");
        }

        this.ganharExperienciaModificador(GANHO_EXP_TREINAR);
        this.consumirEnergiaModificador(CONSUMO_ENERGIA_TREINAR);
        this.consumirSaciedadeModificador(CONSUMO_SACIEDADE_TREINAR);
        this.ultimaAtividade = Atividade.TREINAR;
    }

    @Override
    public void explorar() {
        if (!this.podeExplorar()) {
            throw new IllegalStateException("A criatura não pode explorar agora.");
        }

        this.ganharExperienciaModificador(GANHO_EXP_EXPLORAR);
        this.consumirEnergiaModificador(CONSUMO_ENERGIA_EXPLORAR);
        this.consumirSaciedadeModificador(CONSUMO_SACIEDADE_EXPLORAR);
        this.ganharFelicidadeModificador(GANHO_FELICIDADE_EXPLORAR);
        this.ultimaAtividade = Atividade.EXPLORAR;

    }

    @Override
    public void brincar() {

        if (!this.podeBrincar()) {
            throw new IllegalStateException("A criatura não pode brincar agora.");
        }

        this.ganharFelicidadeModificador(GANHO_FELICIDADE_BRINCAR);
        this.consumirEnergiaModificador(CONSUMO_ENERGIA_BRINCAR);
        this.consumirSaciedadeModificador(CONSUMO_SACIEDADE_BRINCAR);
        this.ultimaAtividade = Atividade.BRINCAR;
    }

    @Override
    public void descansar() {
        if (!this.podeDescansar()) {
            throw new IllegalStateException("A criatura não pode descansar agora.");
        }
        this.ganharEnergiaModificador(GANHO_ENERGIA_DESCANSAR);
        this.ultimaAtividade = Atividade.DESCANSAR;
    }

    @Override
    public void participarDesafio() {
        if (!this.podeParticiparDesafio()) {
            throw new IllegalStateException("A criatura não pode participar de um desafio agora.");
        }
        this.ganharExperienciaModificador(GANHO_EXP_DESAFIAR);
        this.consumirEnergiaModificador(CONSUMO_ENERGIA_DESAFIAR);
        this.consumirSaciedadeModificador(CONSUMO_SACIEDADE_DESAFIAR);
        this.ganharFelicidadeModificador(GANHO_FELICIDADE_DESAFIAR);
        this.desafiosParticipados.add(nivel);
        this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
    }

    @Override
    protected void evoluir() {
        int energiaAntes = this.energia;

        this.ganharEnergia(GANHO_ENERGIA_EVOLUIR);

        this.ultimaAtividade = Atividade.EVOLUIR;

        System.out.println(this.getNome() + " evoluiu. Sua energia antes era " + energiaAntes + " e agora é " + this.getEnergia() + ".");
    }

    @Override
    public void realizarVoo() {
        int felicidadeAntes = this.felicidade;
        this.ganharFelicidade(GANHO_FELICIDADE_VOAR);
        System.out.println(this.nome + " voou. Sua felicidade antes era " + felicidadeAntes + " e agora é " + this.felicidade);
    }

    @Override
    public void executarHabilidadeEspecial() {
        this.realizarVoo();
    }

    @Override
    protected int desgasteEnergia() {
        return DESGASTE_ENERGIA;
    }

    @Override
    protected int desgasteSaciedade() {
        return DESGASTE_SACIEDADE;
    }

    @Override
    protected int desgasteFelicidade() {
        return DESGASTE_FELICIDADE;
    }

}
