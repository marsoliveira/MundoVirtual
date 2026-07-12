package br.ufjf.dcc.util.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritorCSV {

    public static <T> void salvar(String caminho, List<T> objetos, ConversorCSV<T> conversor, String cabecalho) throws IOException {

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {

            escritor.write(cabecalho);
            escritor.newLine();

            for (T objeto : objetos) {

                escritor.write(conversor.serializar(objeto));
                escritor.newLine();
            }
        }
    }
}
