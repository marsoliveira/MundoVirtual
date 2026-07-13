package br.ufjf.dcc.model.criatura;

import java.util.Set;

import br.ufjf.dcc.interfaces.Autodidata;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class DraconisCelestial extends Draconis implements Autodidata {

    private static final int GANHO_EXP_ESTUDAR = 30;

    public DraconisCelestial(String nome, int idade) {
        super(nome, idade);
        this.especie = "Draconis Celestial";
    }

    public DraconisCelestial(String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        super(nome, idade, nivel, experiencia, energia, saciedade, felicidade);
        this.especie = "Draconis Celestial";
    }

    @Override
    public Set<TipoAlimento> getAlimentosCompativeis() {
        return Set.of(TipoAlimento.FOTONS, TipoAlimento.CARNE, TipoAlimento.BANQUETE_REAL);
    }

    @Override
    public void estudar() {
        int experienciaAntes = this.experiencia;

        this.experiencia += GANHO_EXP_ESTUDAR;

        System.out.println(this.nome + " estudou. Sua experiência antes era " + experienciaAntes + " e sua experiência agora é " + this.experiencia);
    }

    @Override
    public void executarHabilidadeEspecial() {
        this.realizarVoo();
        this.estudar();
    }

    public void executarHabilidadeEspecial(int opcao) {

        switch (opcao) {

            case 1 ->
                realizarVoo();

            case 2 ->
                estudar();

            case 3 -> {
                realizarVoo();
                estudar();
            }

            default ->
                System.out.println("Opção inválida.");
        }
    }

}
