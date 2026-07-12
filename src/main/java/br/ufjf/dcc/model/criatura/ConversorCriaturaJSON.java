package br.ufjf.dcc.model.criatura;

import org.json.JSONObject;

import br.ufjf.dcc.util.json.ConversorJSON;

public class ConversorCriaturaJSON implements ConversorJSON<Criatura> {

    @Override
    public Criatura converter(JSONObject json) {

        String nome = json.getString("nome");
        String especie = json.getString("especie");

        int idade = json.getInt("idade");
        int nivel = json.getInt("nivel");
        int experiencia = json.getInt("experiencia");

        int energia = json.getInt("energia");
        int saciedade = json.getInt("saciedade");
        int felicidade = json.getInt("felicidade");

        return InsereCriaturas.criar(especie, nome, idade, nivel, experiencia, energia, saciedade, felicidade);
    }

    @Override
    public JSONObject serializar(Criatura criatura) {

        JSONObject json = new JSONObject();

        json.put("nome", criatura.getNome());
        json.put("especie", criatura.getClass().getSimpleName());

        json.put("idade", criatura.getIdade());
        json.put("nivel", criatura.getNivel());
        json.put("experiencia", criatura.getExperiencia());

        json.put("energia", criatura.getEnergia());
        json.put("saciedade", criatura.getSaciedade());
        json.put("felicidade", criatura.getFelicidade());

        return json;
    }
}
