package br.ufjf.dcc.util.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorCSV {

    public static <T> List<T> carregar(String caminho, ConversorCSV<T> conversor) throws IOException {

        List<T> itens = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {

            leitor.readLine();

            String linha;

            while ((linha = leitor.readLine()) != null) {
                itens.add(conversor.converter(linha));
            }
        }

        return itens;
    }
}
