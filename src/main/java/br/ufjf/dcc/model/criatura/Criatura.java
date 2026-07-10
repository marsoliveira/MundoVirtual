package br.ufjf.dcc.model.criatura;

public abstract class Criatura {

    protected String nome;
    protected int idade;
    protected int nivel;
    protected int experiencia;
    protected int energia;
    protected int saciedade;
    protected int felicidade;
    protected int saude;
    protected boolean vivo;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getExperiencia() {
        return this.experiencia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getEnergia() {
        return this.energia;
    }

    public void setSaciedade(int saciedade) {
        this.saciedade = saciedade;
    }

    public int getSaciedade() {
        return this.saciedade;
    }

    public void setFelicidade(int felicidade) {
        this.felicidade = felicidade;
    }

    public int getFelicidade() {
        return this.felicidade;
    }

    public void setSaude(int saude) {
        this.saude = saude;
    }

    public int getSaude() {
        return this.saude;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean seVivo() {
        return this.vivo;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Nível: " + this.nivel);
        System.out.println("Experiência: " + this.experiencia);
        System.out.println("Energia: " + this.energia);
        System.out.println("Saciedade: " + this.saciedade);
        System.out.println("Felicidade: " + this.felicidade);
        System.out.println("Saúde: " + this.saude);
        System.out.println("Vivo: " + (this.seVivo() ? "Sim" : "Não"));
    }

}
