package br.ufjf.dcc.util.csv;

import br.ufjf.dcc.model.estatistica.DadoEstatistico;

public class ConversorEstatisticaCSV implements ConversorCSV<DadoEstatistico> {

    @Override
    public DadoEstatistico converter(String linha) {
        String[] dados = linha.split(";");

        return new DadoEstatistico(dados[0], dados[1]);
    }

    @Override
    public String serializar(DadoEstatistico dado) {
        return dado.getNome() + ";" + dado.getValor();
    }
}
