package br.ufjf.dcc.model.estatistica;

import java.util.HashMap;
import java.util.Map;

import br.ufjf.dcc.model.alimento.Estoque;
import br.ufjf.dcc.model.criatura.Criatura;
import br.ufjf.dcc.model.criatura.ListaCriaturas;
import br.ufjf.dcc.model.enums.TipoAlimento;
import br.ufjf.dcc.model.tempo.PassagemTempo;

public class Estatistica {

    private ListaCriaturas listaCriaturas;
    private PassagemTempo mundoVirtual;
    private Estoque estoque;

    public Estatistica(ListaCriaturas listaCriaturas, PassagemTempo mundoVirtual, Estoque estoque) {
        this.listaCriaturas = listaCriaturas;
        this.mundoVirtual = mundoVirtual;
        this.estoque = estoque;
    }

    public int getQteTotalCriaturas() {
        return listaCriaturas.getQteTotalCriaturas();
    }

    public int getQteCriaturasVivas() {
        return listaCriaturas.getQteCriaturasVivas();
    }

    public int getQteCriaturasMortas() {
        return listaCriaturas.getQteCriaturasMortas();
    }

    public Criatura getCriaturaMaiorNivel() {
        return listaCriaturas.getCriaturaMaiorNivel();
    }

    public Criatura getCriaturaMenorNivel() {
        return listaCriaturas.getCriaturaMenorNivel();
    }

    public int getTurnoAtual() {
        return mundoVirtual.getTurno();
    }

    public Map<String, Integer> getQtePorEspecie() {

        Map<String, Integer> quantidades = new HashMap<>();

        for (Criatura criatura : listaCriaturas.getCriaturas()) {

            String especie = criatura.getEspecie();

            quantidades.put(especie, quantidades.getOrDefault(especie, 0) + 1);
        }

        return quantidades;
    }

    public Map<TipoAlimento, Integer> getEstoqueAlimentos() {

        Map<TipoAlimento, Integer> alimentos = new HashMap<>();

        for (TipoAlimento tipo : TipoAlimento.values()) {

            alimentos.put(tipo, estoque.getQteDisponivelAlimento(tipo));
        }

        return alimentos;
    }

    public void imprimirEstatisticas() {

        System.out.println("ESTATÍSTICAS");

        System.out.println("Turno atual: " + getTurnoAtual());

        System.out.println("Total de criaturas: " + getQteTotalCriaturas());

        System.out.println("Criaturas vivas: " + getQteCriaturasVivas());

        System.out.println("Criaturas mortas: " + getQteCriaturasMortas());

        Criatura criaturaMaiorNivel = getCriaturaMaiorNivel();

        System.out.println("Criatura com maior nível: " + criaturaMaiorNivel.getNome() + " (nível " + criaturaMaiorNivel.getNivel() + ")");

        Criatura criaturaMenorNivel = getCriaturaMenorNivel();

        System.out.println("Criatura com menor nível: " + criaturaMenorNivel.getNome() + " (nível " + criaturaMenorNivel.getNivel() + ")");

        System.out.println("Quantidade por espécie");

        for (Map.Entry<String, Integer> entrada : getQtePorEspecie().entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }

        System.out.println("Estoque de alimentos");

        for (Map.Entry<TipoAlimento, Integer> entrada : getEstoqueAlimentos().entrySet()) {
            System.out.println(entrada.getKey().getDescricao() + ": " + entrada.getValue());
        }
    }
}
