package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.interfaces.Aquatico;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Aquari extends Criatura implements Aquatico {

    private static final int GANHO_EXP_TREINAR = 25;
    private static final int CONSUMO_ENERGIA_TREINAR = 15;
    private static final int CONSUMO_SACIEDADE_TREINAR = 10;

    private static final int GANHO_EXP_EXPLORAR = 15;
    private static final int CONSUMO_ENERGIA_EXPLORAR = 10;
    private static final int CONSUMO_SACIEDADE_EXPLORAR = 10;
    private static final int GANHO_FELICIDADE_EXPLORAR = 15;

    private static final int GANHO_FELICIDADE_BRINCAR = 20;
    private static final int CONSUMO_ENERGIA_BRINCAR = 10;
    private static final int CONSUMO_SACIEDADE_BRINCAR = 5;

    private static final int GANHO_ENERGIA_DESCANSAR = 10;

    private static final int GANHO_EXP_DESAFIAR = 45;
    private static final int CONSUMO_ENERGIA_DESAFIAR = 20;
    private static final int CONSUMO_SACIEDADE_DESAFIAR = 20;
    private static final int GANHO_FELICIDADE_DESAFIAR = 10;

    private static final int GANHO_ENERGIA_EVOLUIR = 1;
    private static final int GANHO_FELICIDADE_EVOLUIR = 1;

    private static final int GANHO_FELICIDADE_MERGULHAR = 10;

    private static final int DESGASTE_ENERGIA = 4;
    private static final int DESGASTE_SACIEDADE = 3;
    private static final int DESGASTE_FELICIDADE = 2;

    public Aquari(String nome, int idade) {
        super(nome, "Aquari", idade);
    }

    public Aquari(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, "Aquari", idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public void treinar() {
        if (this.podeTreinar()) {
            this.ganharExperienciaModificador(GANHO_EXP_TREINAR);
            this.consumirEnergiaModificador(CONSUMO_ENERGIA_TREINAR);
            this.consumirSaciedadeModificador(CONSUMO_SACIEDADE_TREINAR);
            this.ultimaAtividade = Atividade.TREINAR;
        }
    }

    @Override
    public void explorar() {
        if (this.podeExplorar()) {
            this.ganharExperienciaModificador(GANHO_EXP_EXPLORAR);
            this.consumirEnergiaModificador(CONSUMO_ENERGIA_EXPLORAR);
            this.consumirSaciedadeModificador(CONSUMO_SACIEDADE_EXPLORAR);
            this.ganharFelicidadeModificador(GANHO_FELICIDADE_EXPLORAR);
            this.ultimaAtividade = Atividade.EXPLORAR;
        }
    }

    @Override
    public void brincar() {
        if (this.podeBrincar()) {
            this.ganharFelicidadeModificador(GANHO_FELICIDADE_BRINCAR);
            this.consumirEnergiaModificador(CONSUMO_ENERGIA_BRINCAR);
            this.consumirSaciedadeModificador(CONSUMO_SACIEDADE_BRINCAR);
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
            this.ganharEnergiaModificador(GANHO_ENERGIA_DESCANSAR);
            this.ultimaAtividade = Atividade.DESCANSAR;
        }
    }

    @Override
    public void participarDesafio() {
        if (this.podeParticiparDesafio()) {
            this.ganharExperienciaModificador(GANHO_EXP_DESAFIAR);
            this.consumirEnergiaModificador(CONSUMO_ENERGIA_DESAFIAR);
            this.consumirSaciedadeModificador(CONSUMO_SACIEDADE_DESAFIAR);
            this.ganharFelicidadeModificador(GANHO_FELICIDADE_DESAFIAR);
            this.desafiosParticipados.add(nivel);
            this.ultimaAtividade = Atividade.PARTICIPAR_DESAFIO;
        }
    }

    @Override
    protected void evoluir() {
        int energiaAntes = this.energia;
        int felicidadeAntes = this.felicidade;

		this.ganharEnergia(GANHO_ENERGIA_EVOLUIR);
		this.ganharFelicidade(GANHO_FELICIDADE_EVOLUIR);

        this.ultimaAtividade = Atividade.EVOLUIR;

        System.out.println(this.getNome() + " evoluiu. Sua energia e felicidade antes eram " + energiaAntes + " e " + felicidadeAntes + ", respectivamente, e agora são " + this.getEnergia() + " e " + this.getFelicidade() + ".");
    }

    @Override
    public void mergulhar() {
        int felicidadeAntes = this.felicidade;
		this.ganharFelicidade(GANHO_FELICIDADE_MERGULHAR);
        System.out.println(this.nome + " mergulhou. Sua felicidade antes era " + felicidadeAntes + " e agora é " + this.felicidade);
    }

    @Override
    public void executarHabilidadeEspecial() {
        this.mergulhar();
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
