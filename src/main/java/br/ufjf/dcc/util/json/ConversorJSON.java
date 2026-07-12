package br.ufjf.dcc.util.json;

import org.json.JSONObject;

public interface ConversorJSON<T> {

    T converter(JSONObject json);

    JSONObject serializar(T objeto);

}