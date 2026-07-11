package br.ufjf.dcc.util;

public interface ConversorCSV<T> {

    T converter(String linha);

}
