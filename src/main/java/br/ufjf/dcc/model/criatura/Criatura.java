package br.ufjf.dcc.model.criatura;

import java.util.HashSet;
import java.util.Set;

import br.ufjf.dcc.model.alimento.Estoque;
import br.ufjf.dcc.model.enums.Atividade;
import br.ufjf.dcc.model.enums.EstadoEnergia;
import br.ufjf.dcc.model.enums.EstadoFelicidade;
import br.ufjf.dcc.model.enums.EstadoSaciedade;
import br.ufjf.dcc.model.enums.EstadoSaude;
import br.ufjf.dcc.model.enums.TipoAlimento;

public abstract class Criatura {

    protected String nome;
    protected String especie;
    protected int idade;
    protected int nivel;
    protected int experiencia;
    protected int energia;
    protected int saciedade;
    protected int felicidade;
    protected int saude;
    protected boolean vivo;
    protected Atividade ultimaAtividade;
    protected Set<Integer> desafiosParticipados;

    public Criatura(String nome, String especie, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.energia = energia;
        this.saciedade = saciedade;
        this.felicidade = felicidade;
        this.saude = (energia + saciedade + felicidade) / 3;
        this.vivo = true;
        this.desafiosParticipados = new HashSet<>();
    }

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

    public EstadoSaude getEstadoSaude() {

        if (this.saude == 0) {
            return EstadoSaude.MORTA;
        }

        if (this.saude < 40) {
            return EstadoSaude.DOENTE;
        }

        if ((this.saude >= 40) && (this.saude <= 70)) {
            return EstadoSaude.ATENCAO;
        }

        return EstadoSaude.SAUDAVEL;
    }

    public EstadoEnergia getEstadoEnergia() {

        if (this.energia < 50) {
            return EstadoEnergia.CANSADA;
        }

        return EstadoEnergia.DISPOSTA;
    }

    public EstadoSaciedade getEstadoSaciedade() {

        if (this.saciedade < 50) {
            return EstadoSaciedade.FAMINTA;
        }

        return EstadoSaciedade.SATISFEITA;
    }

    public EstadoFelicidade getEstadoFelicidade() {

        if (this.felicidade < 50) {
            return EstadoFelicidade.TRISTE;
        }

        return EstadoFelicidade.FELIZ;
    }

    public void exibirEstadoAtual() {
        System.out.println("Estado atual de " + nome + ":");
        System.out.println("Saúde: " + getEstadoSaude().getDescricao());
        System.out.println("Energia: " + getEstadoEnergia().getDescricao());
        System.out.println("Saciedade: " + getEstadoSaciedade().getDescricao());
        System.out.println("Felicidade: " + getEstadoFelicidade().getDescricao());
    }

    protected boolean sePodeTreinar() {
        return this.saude > 40 && this.energia >= 20 && this.saciedade >= 20;
    }

    protected boolean sePodeExplorar() {
        return this.saude > 20 && this.energia >= 15 && this.saciedade >= 15;
    }

    protected boolean sePodeBrincar() {
        return this.saude > 20 && this.energia >= 50 && this.saciedade >= 50;
    }

    protected abstract boolean aceitaAlimento(TipoAlimento tipoAlimento);

    protected boolean sePodeAlimentar(TipoAlimento tipoAlimento) {
        return this.saude > 0 && this.saciedade < 90 && aceitaAlimento(tipoAlimento);
    }

    protected boolean sePodeDescansar() {
        return this.saude > 0 && this.energia < 90 && this.ultimaAtividade != Atividade.DESCANSAR;
    }

    protected boolean sePodeParticiparDesafio() {
        return this.saude > 40 && this.nivel >= 5 && this.nivel % 15 == 0 && this.energia >= 50 && this.saciedade >= 50 && !this.desafiosParticipados.contains(nivel);
    }

    protected boolean sePodeEvoluir() {
        return this.experiencia >= 100;
    }

    public abstract void treinar();

    public abstract void explorar();

    public abstract void brincar();

    public void alimentar(TipoAlimento tipoAlimento, Estoque estoque) {

        if (!sePodeAlimentar(tipoAlimento)) {
            System.out.println("A criatura não pode comer esse alimento.");
            return;
        }

        if (!estoque.possui(tipoAlimento)) {
            System.out.println("Não há esse alimento no estoque.");
            return;
        }

        estoque.consumir(tipoAlimento);

        this.saciedade += tipoAlimento.getSaciedade();

        this.ultimaAtividade = Atividade.ALIMENTAR;

        System.out.println(this.nome + " se alimentou.");
    }

    public abstract void descansar();

    public abstract void participarDesafio();

    public abstract void evoluir();

}
