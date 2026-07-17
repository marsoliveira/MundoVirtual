package br.ufjf.dcc;

import java.io.IOException;

import br.ufjf.dcc.model.alimento.Estoque;
import br.ufjf.dcc.model.criatura.ListaCriaturas;
import br.ufjf.dcc.service.Sistema;

public class Main {

    public static void main(String[] args) throws IOException {

        //ListaCriaturas listaCriaturas = new ListaCriaturas("src/main/resources/criaturas.json");

		ListaCriaturas listaCriaturas = new ListaCriaturas();

        Estoque estoque = new Estoque("src/main/resources/alimentos.csv");

        Sistema sistema = new Sistema(listaCriaturas, estoque);

        sistema.iniciar();
    }
}
