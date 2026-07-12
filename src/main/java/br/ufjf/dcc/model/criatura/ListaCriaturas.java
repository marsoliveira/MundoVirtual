package br.ufjf.dcc.model.criatura;

import java.util.ArrayList;
import java.util.List;

public class ListaCriaturas {

    private List<Criatura> criaturas;

    public ListaCriaturas() {
        this.criaturas = new ArrayList<>();
    }

    public List<Criatura> getCriaturas() {
        return this.criaturas;
    }

    public void exibeCriaturas() {
        if (criaturas.isEmpty()) {
            System.out.println("Nenhuma criatura cadastrada.");
        } else {
            System.out.println("Criaturas cadastradas:");
            for (int i = 0; i < criaturas.size(); i++) {
                Criatura criatura = criaturas.get(i);
                System.out.println((i + 1) + ". " + criatura.getNome() + " - " + criatura.getEspecie());
            }
        }
    }

    public void criarCriatura(Criatura criatura) {
        this.criaturas.add(criatura);
    }

    public void removerCriatura(Criatura criatura) {
        this.criaturas.remove(criatura);
    }

    public int getQteTotalCriaturas() {
        return criaturas.size();
    }

    public int getQteCriaturasPorEspecie(String especie) {
        int qteCriaturasPorEspecie = 0;

        for (Criatura criatura : criaturas) {
            if (criatura.getEspecie().equalsIgnoreCase(especie)) {
                qteCriaturasPorEspecie++;
            }
        }

        return qteCriaturasPorEspecie;
    }

    public List<Criatura> getCriaturasPorEspecie(Class<? extends Criatura> especie) {

        List<Criatura> criaturasPorEspecie = new ArrayList<>();

        for (Criatura criatura : criaturas) {

            if (especie.isInstance(criatura)) {
                criaturasPorEspecie.add(criatura);
            }
        }

        return criaturasPorEspecie;
    }

    public Criatura getCriaturaMaiorNivel() {
        int maiorNivel = 0;
        Criatura criaturaMaiorNivel = null;

        for (Criatura criatura : criaturas) {
            if (criatura.getNivel() > maiorNivel) {
                maiorNivel = criatura.getNivel();
                criaturaMaiorNivel = criatura;
            }
        }

        return criaturaMaiorNivel;
    }

    public int getQteCriaturasVivas() {
        int qteCriaturasVivas = 0;

        for (Criatura criatura : criaturas) {
            if (criatura.seViva()) {
                qteCriaturasVivas++;
            }
        }

        return qteCriaturasVivas;
    }

    public int getQteCriaturasMortas() {
        int qteCriaturasMortas = 0;

        for (Criatura criatura : criaturas) {
            if (!criatura.seViva()) {
                qteCriaturasMortas++;
            }
        }

        return qteCriaturasMortas;
    }
}
