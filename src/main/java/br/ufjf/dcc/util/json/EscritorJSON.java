package br.ufjf.dcc.util.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.json.JSONArray;

public class EscritorJSON {

    public static <T> void salvar(String caminho, List<T> objetos, ConversorJSON<T> conversor) throws IOException {

        JSONArray array = new JSONArray();

        for (T objeto : objetos) {

            array.put(conversor.serializar(objeto));
        }

        Files.writeString(Path.of(caminho), array.toString(4));
    }

}
