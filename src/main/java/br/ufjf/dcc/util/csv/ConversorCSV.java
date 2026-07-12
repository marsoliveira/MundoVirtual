package br.ufjf.dcc.util.csv;

public interface ConversorCSV<T> {

    T converter(String linha);

    String serializar(T objeto);

}
