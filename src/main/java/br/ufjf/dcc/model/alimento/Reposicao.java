package br.ufjf.dcc.model.alimento;

import java.util.List;
import java.util.Set;

import br.ufjf.dcc.model.criatura.Aquari;
import br.ufjf.dcc.model.criatura.Criatura;
import br.ufjf.dcc.model.criatura.Draconis;
import br.ufjf.dcc.model.criatura.DraconisCelestial;
import br.ufjf.dcc.model.criatura.Fungari;
import br.ufjf.dcc.model.criatura.ListaCriaturas;
import br.ufjf.dcc.model.criatura.Lumini;
import br.ufjf.dcc.model.criatura.Mecanis;
import br.ufjf.dcc.model.enums.TipoAlimento;

public class Reposicao {

    private Estoque estoque;
    private ListaCriaturas listaCriaturas;

    public Reposicao(Estoque estoque, ListaCriaturas listaCriaturas) {
        this.estoque = estoque;
        this.listaCriaturas = listaCriaturas;
    }

    public void executar() {
        verificarEspecie(Aquari.class);
        verificarEspecie(Draconis.class);
        verificarEspecie(DraconisCelestial.class);
        verificarEspecie(Fungari.class);
        verificarEspecie(Lumini.class);
        verificarEspecie(Mecanis.class);
    }

    private void verificarEspecie(Class<? extends Criatura> especie) {
        List<Criatura> criaturas = listaCriaturas.getCriaturasPorEspecie(especie);

        int quantidadeCriaturas = criaturas.size();

        if (quantidadeCriaturas == 0) {
            return;
        }

        int estoqueMinimo = quantidadeCriaturas * 3;

        Set<TipoAlimento> alimentosCompativeis = criaturas.get(0).getAlimentosCompativeis();

        int estoqueDisponivel = 0;

        for (TipoAlimento tipo : alimentosCompativeis) {
            estoqueDisponivel += estoque.getQteDisponivelAlimento(tipo);
        }

        if (estoqueDisponivel < estoqueMinimo) {
            repor(alimentosCompativeis);
        }
    }

    private void repor(Set<TipoAlimento> alimentos) {
        int quantidade = 10 / alimentos.size();
        int resto = 10 % alimentos.size();

        boolean primeiro = true;

        for (TipoAlimento tipo : alimentos) {

            if (primeiro) {
                quantidade += resto;
                primeiro = false;
            }

            estoque.adicionar(tipo, quantidade);
        }
    }
}
