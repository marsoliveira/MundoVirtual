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
        this.vivo = true;
        this.atualizarSaude();
        this.desafiosParticipados = new HashSet<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEspecie() {
        return this.especie;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdade() {
        return this.idade;
    }

    public int getNivel() {
        return this.nivel;
    }

    public int getExperiencia() {
        return this.experiencia;
    }

    public int getEnergia() {
        return this.energia;
    }

    public int getSaciedade() {
        return this.saciedade;
    }

    public int getFelicidade() {
        return this.felicidade;
    }

    public int getSaude() {
        return this.saude;
    }

    public boolean seViva() {
        return this.vivo;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Espécie: " + this.especie);
        System.out.println("Idade: " + this.idade);
        System.out.println("Nível: " + this.nivel);
        System.out.println("Experiência: " + this.experiencia);
        System.out.println("Energia: " + this.energia);
        System.out.println("Saciedade: " + this.saciedade);
        System.out.println("Felicidade: " + this.felicidade);
        System.out.println("Saúde: " + this.saude);
        System.out.println("Vivo: " + (this.seViva() ? "Sim" : "Não"));
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

    protected void atualizarSaude() {

        if (this.energia <= 0 || this.saciedade <= 0 || this.felicidade <= 0) {
            this.saude = 0;
            this.vivo = false;

            return;
        }

        this.saude = (this.energia + this.saciedade + this.felicidade) / 3;
    }

    protected boolean sePodeTreinar() {
        return this.seViva() && this.saude > 40 && this.energia >= 20 && this.saciedade >= 20;
    }

    protected boolean sePodeExplorar() {
        return this.seViva() && this.saude > 20 && this.energia >= 15 && this.saciedade >= 15;
    }

    protected boolean sePodeBrincar() {
        return this.seViva() && this.saude > 20 && this.energia >= 50 && this.saciedade >= 50;
    }

    protected boolean sePodeDescansar() {
        return this.seViva() && this.saude > 0 && this.energia < 90 && this.ultimaAtividade != Atividade.DESCANSAR;
    }

    protected boolean sePodeParticiparDesafio() {
        return this.seViva() && this.saude > 40 && this.nivel >= 5 && this.nivel % 15 == 0 && this.energia >= 50 && this.saciedade >= 50 && !this.desafiosParticipados.contains(nivel);
    }

    protected boolean aceitaAlimento(TipoAlimento tipoAlimento) {
        return this.getAlimentosCompativeis().contains(tipoAlimento);
    }

    protected boolean sePodeAlimentar(TipoAlimento tipoAlimento) {
        return this.seViva() && this.saude > 0 && this.saciedade < 90 && aceitaAlimento(tipoAlimento);
    }

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

        this.ganharSaciedade(tipoAlimento.getSaciedade());

        this.ultimaAtividade = Atividade.ALIMENTAR;

        System.out.println(this.nome + " se alimentou.");
    }

    protected int calcularModificadorNivel() {
        return Math.min(this.nivel / 10, 9);
    }

    protected int calcularConsumoAtributo(int valorBase) {
        return valorBase + calcularModificadorNivel();
    }

    protected int calcularGanhoAtributo(int valorBase) {
        return valorBase - calcularModificadorNivel();
    }

    protected void consumirEnergia(int valorBase) {
        this.energia -= calcularConsumoAtributo(valorBase);
        this.atualizarSaude();
    }

    protected void ganharEnergia(int quantidade) {
        this.energia += quantidade;
        this.atualizarSaude();
    }

    protected void consumirSaciedade(int valorBase) {
        this.saciedade -= calcularConsumoAtributo(valorBase);
        this.atualizarSaude();
    }

    protected void ganharSaciedade(int quantidade) {
        this.saciedade += quantidade;
        this.atualizarSaude();
    }

    protected void ganharFelicidade(int valorBase) {
        this.felicidade += calcularGanhoAtributo(valorBase);
        this.atualizarSaude();
    }

    protected void consumirFelicidade(int quantidade) {
        this.felicidade -= quantidade;
        this.atualizarSaude();
    }

    protected void ganharExperiencia(int valorBase) {
        this.experiencia += calcularGanhoAtributo(valorBase);

        while (this.experiencia >= 100) {
            this.experiencia -= 100;
            this.nivel++;

            this.evoluir();
        }
    }

    public void aplicarDesgasteNatural() {
        this.energia -= desgasteEnergia();
        this.saciedade = desgasteSaciedade();
        this.felicidade = desgasteFelicidade();

        this.atualizarSaude();
    }

    public abstract Set<TipoAlimento> getAlimentosCompativeis();

    protected abstract void treinar();

    protected abstract void explorar();

    protected abstract void brincar();

    protected abstract void descansar();

    protected abstract void participarDesafio();

    protected abstract void evoluir();

    protected abstract int desgasteEnergia();

    protected abstract int desgasteSaciedade();

    protected abstract int desgasteFelicidade();

}
