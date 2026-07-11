package br.ufjf.dcc.model.alimento;

import java.io.IOException;
import java.util.List;

import br.ufjf.dcc.util.LeitorCSV;

public class Estoque {

    public static List<Alimento> carregarAlimentos(String caminho) throws IOException {

        return LeitorCSV.carregar(caminho, new ConversorAlimentoCSV());
    }
}
