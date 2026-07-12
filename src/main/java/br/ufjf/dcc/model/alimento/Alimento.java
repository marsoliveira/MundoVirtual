package br.ufjf.dcc.model.alimento;

import br.ufjf.dcc.model.enums.TipoAlimento;

public class Alimento {

    private TipoAlimento tipo;
    private int quantidade;

    public Alimento(TipoAlimento tipo, int quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public void setTipo(TipoAlimento tipo) {
        this.tipo = tipo;
    }

    public TipoAlimento getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionar(int quantidade) {
        this.quantidade += quantidade;
    }

    public void consumir(int quantidadeConsumida) {
        if (quantidadeConsumida <= quantidade) {
            quantidade -= quantidadeConsumida;
        } else {
            System.out.println("Quantidade insuficiente de " + tipo.getDescricao() + " no estoque.");
        }
    }

    @Override
    public String toString() {
        return tipo.getDescricao() + " (" + quantidade + ")";
    }

}
