package br.ufjf.dcc.model.criatura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc.util.json.LeitorJSON;

public class ListaCriaturas {

    private List<Criatura> criaturas;

    public ListaCriaturas() {
        this.criaturas = new ArrayList<>();
    }

    public ListaCriaturas(String caminho) throws IOException {
        this.criaturas = LeitorJSON.carregar(caminho, new ConversorCriaturaJSON());
    }

    public List<Criatura> getCriaturas() {
        return this.criaturas;
    }

    public void exibirCriaturas() {
        if (criaturas.isEmpty()) {
            System.out.println("Nenhuma criatura cadastrada.");
        } else {
            System.out.println("Criaturas cadastradas:");
            System.out.println("0. Voltar");
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
        if (criaturas.isEmpty()) {
            return null;
        }

        Criatura criaturaMaiorNivel = criaturas.get(0);
        int maiorNivel = criaturaMaiorNivel.getNivel();

        for (Criatura criatura : criaturas) {

            if (criatura.getNivel() > maiorNivel) {

                maiorNivel = criatura.getNivel();
                criaturaMaiorNivel = criatura;
            }
        }

        return criaturaMaiorNivel;
    }

    public Criatura getCriaturaMenorNivel() {
        if (criaturas.isEmpty()) {
            return null;
        }

        Criatura criaturaMenorNivel = criaturas.get(0);
        int menorNivel = criaturaMenorNivel.getNivel();

        for (Criatura criatura : criaturas) {

            if (criatura.getNivel() < menorNivel) {

                menorNivel = criatura.getNivel();
                criaturaMenorNivel = criatura;
            }
        }

        return criaturaMenorNivel;
    }

    public int getQteCriaturasVivas() {
        int qteCriaturasVivas = 0;

        for (Criatura criatura : criaturas) {
            if (criatura.estaViva()) {
                qteCriaturasVivas++;
            }
        }

        return qteCriaturasVivas;
    }

    public int getQteCriaturasMortas() {
        int qteCriaturasMortas = 0;

        for (Criatura criatura : criaturas) {
            if (!criatura.estaViva()) {
                qteCriaturasMortas++;
            }
        }

        return qteCriaturasMortas;
    }
}
