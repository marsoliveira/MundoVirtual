package br.ufjf.dcc.util.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class LeitorJSON {

    public static <T> List<T> carregar(String caminho, ConversorJSON<T> conversor) throws IOException {

        String conteudo = Files.readString(Path.of(caminho));

        JSONArray array = new JSONArray(conteudo);

        List<T> objetos = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {

            JSONObject json = array.getJSONObject(i);

            objetos.add(conversor.converter(json));
        }

        return objetos;
    }

}
