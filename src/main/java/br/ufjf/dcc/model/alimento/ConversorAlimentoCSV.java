package br.ufjf.dcc.model.alimento;

import br.ufjf.dcc.model.enums.TipoAlimento;
import br.ufjf.dcc.util.ConversorCSV;

public class ConversorAlimentoCSV implements ConversorCSV<Alimento> {

    @Override
    public Alimento converter(String linha) {

        String[] dados = linha.split("\t");

        TipoAlimento tipo = TipoAlimento.obterPorDescricao(dados[0]);

        int quantidade = Integer.parseInt(dados[1]);

        return new Alimento(tipo, quantidade);
    }
}
