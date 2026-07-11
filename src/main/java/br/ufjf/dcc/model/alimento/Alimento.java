package br.ufjf.dcc.model.alimento;

public class Alimento {

    private String nome;
    private int quantidade;

    public Alimento(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
        return nome;
    }

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
        return quantidade;
    }

	@Override
    public String toString() {
        return nome + " (" + quantidade + ")";
    }
}
