package br.ufjf.dcc.model.estatistica;

public class DadoEstatistico {

    private String nome;
    private String valor;

    public DadoEstatistico(String nome, String valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }
}
