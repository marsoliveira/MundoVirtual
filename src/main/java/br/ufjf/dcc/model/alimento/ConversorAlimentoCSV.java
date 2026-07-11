package br.ufjf.dcc.model.alimento;

import br.ufjf.dcc.util.ConversorCSV;

public class ConversorAlimentoCSV implements ConversorCSV<Alimento> {

    @Override
    public Alimento converter(String linha) {

        String[] dados = linha.split("\t");

        return new Alimento(dados[0], Integer.parseInt(dados[1]));
    }
}
