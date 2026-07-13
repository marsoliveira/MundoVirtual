package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.interfaces.Luminescente;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Lumini extends Criatura implements Luminescente {

    private static final int GANHO_EXP_TREINAR = 25;
    private static final int CONSUMO_ENERGIA_TREINAR = 20;
    private static final int CONSUMO_SACIEDADE_TREINAR = 10;

    private static final int GANHO_EXP_EXPLORAR = 15;
    private static final int CONSUMO_ENERGIA_EXPLORAR = 20;
    private static final int CONSUMO_SACIEDADE_EXPLORAR = 5;
    private static final int GANHO_FELICIDADE_EXPLORAR = 10;

    private static final int GANHO_FELICIDADE_BRINCAR = 35;
    private static final int CONSUMO_ENERGIA_BRINCAR = 5;
    private static final int CONSUMO_SACIEDADE_BRINCAR = 5;

    private static final int GANHO_ENERGIA_DESCANSAR = 40;

    private static final int GANHO_EXP_DESAFIAR = 45;
    private static final int CONSUMO_ENERGIA_DESAFIAR = 35;
    private static final int CONSUMO_SACIEDADE_DESAFIAR = 15;
    private static final int GANHO_FELICIDADE_DESAFIAR = 15;

    private static final int GANHO_FELICIDADE_EVOLUIR = 2;

    private static final int GANHO_FELICIDADE_EMITIR_LUZ = 10;

    private static final int DESGASTE_ENERGIA = 8;
    private static final int DESGASTE_SACIEDADE = 2;
    private static final int DESGASTE_FELICIDADE = 3;

    public Lumini(String nome, int idade) {
        super(nome, "Lumini", idade);
    }

    public Lumini(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Lumini", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.podeTreinar()) {
            this.ganharExperiencia(GANHO_EXP_TREINAR);
            this.consumirEnergia(CONSUMO_ENERGIA_TREINAR);
            this.consumirSaciedade(CONSUMO_SACIEDADE_TREINAR);
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.podeExplorar()) {
            this.ganharExperiencia(GANHO_EXP_EXPLORAR);
            this.consumirEnergia(CONSUMO_ENERGIA_EXPLORAR);
            this.consumirSaciedade(CONSUMO_SACIEDADE_EXPLORAR);
            this.ganharFelicidade(GANHO_FELICIDADE_EXPLORAR);
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.podeBrincar()) {
            this.ganharFelicidade(GANHO_FELICIDADE_BRINCAR);
            this.consumirEnergia(CONSUMO_ENERGIA_BRINCAR);
            this.consumirSaciedade(CONSUMO_SACIEDADE_BRINCAR);
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
            this.ganharEnergia(GANHO_ENERGIA_DESCANSAR);
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.podeParticiparDesafio()) {
            this.ganharExperiencia(GANHO_EXP_DESAFIAR);
            this.consumirEnergia(CONSUMO_ENERGIA_DESAFIAR);
            this.consumirSaciedade(CONSUMO_SACIEDADE_DESAFIAR);
            this.ganharFelicidade(GANHO_FELICIDADE_DESAFIAR);
            this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    protected void evoluir() {
        int felicidadeAntes = this.felicidade;

        this.felicidade += GANHO_FELICIDADE_EVOLUIR;

        this.ultimaAtividade = Atividade.EVOLUIR;

        System.out.println(this.getNome() + " evoluiu. Sua felicidade antes era " + felicidadeAntes + " e agora é " + this.getFelicidade() + ".");
    }

    @Override
    public void emitirLuz() {
        int felicidadeAntes = this.felicidade;
        this.felicidade += GANHO_FELICIDADE_EMITIR_LUZ;
        System.out.println(this.nome + " emitiu luz. Sua felicidade antes era " + felicidadeAntes + " e agora é " + this.felicidade);
    }

    @Override
    public void executarHabilidadeEspecial() {
        this.emitirLuz();
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
