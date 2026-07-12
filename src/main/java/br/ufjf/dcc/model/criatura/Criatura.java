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

    private static final int ESTADO_SAUDE_MORTA = 0;
    private static final int ESTADO_SAUDE_DOENTE = 40;
    private static final int ESTADO_SAUDE_ATENCAO = 70;

    private static final int ESTADO_ENERGIA_CANSADA = 50;
    private static final int ESTADO_SACIEDADE_FAMINTA = 50;
    private static final int ESTADO_FELICIDADE_TRISTE = 50;

    private static final int SAUDE_TREINAR = 40;
    private static final int ENERGIA_TREINAR = 20;
    private static final int SACIEDADE_TREINAR = 20;

    private static final int SAUDE_EXPLORAR = 20;
    private static final int ENERGIA_EXPLORAR = 15;
    private static final int SACIEDADE_EXPLORAR = 15;

    private static final int SAUDE_BRINCAR = 20;
    private static final int ENERGIA_BRINCAR = 50;
    private static final int SACIEDADE_BRINCAR = 50;

    private static final int SAUDE_DESCANSAR = 0;
    private static final int ENERGIA_DESCANSAR = 90;

    private static final int SAUDE_DESAFIAR = 40;
    private static final int ENERGIA_DESAFIAR = 50;
    private static final int SACIEDADE_DESAFIAR = 50;
    private static final int NIVEL_MIN_DESAFIAR = 5;
    private static final int NIVEL_DESAFIAR = 15;

    private static final int SAUDE_ALIMENTAR = 0;
    private static final int SACIEDADE_ALIMENTAR = 90;

    private static final int MODIFICADOR = 10;
    private static final int LIM_MODIFICADOR = 9;

    private static final int MAX_EXPERIENCIA = 9;

    private static final int ENERGIA_MORTA = 0;
    private static final int SACIEDADE_MORTA = 0;
    private static final int FELICIDADE_MORTA = 0;

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

    public boolean estaViva() {
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
        System.out.println("Vivo: " + (this.estaViva() ? "Sim" : "Não"));
    }

    public EstadoSaude getEstadoSaude() {

        if (this.saude == ESTADO_SAUDE_MORTA) {
            return EstadoSaude.MORTA;
        }

        if (this.saude < ESTADO_SAUDE_DOENTE) {
            return EstadoSaude.DOENTE;
        }

        if ((this.saude <= ESTADO_SAUDE_ATENCAO)) {
            return EstadoSaude.ATENCAO;
        }

        return EstadoSaude.SAUDAVEL;
    }

    public EstadoEnergia getEstadoEnergia() {

        if (this.energia < ESTADO_ENERGIA_CANSADA) {
            return EstadoEnergia.CANSADA;
        }

        return EstadoEnergia.DISPOSTA;
    }

    public EstadoSaciedade getEstadoSaciedade() {

        if (this.saciedade < ESTADO_SACIEDADE_FAMINTA) {
            return EstadoSaciedade.FAMINTA;
        }

        return EstadoSaciedade.SATISFEITA;
    }

    public EstadoFelicidade getEstadoFelicidade() {

        if (this.felicidade < ESTADO_FELICIDADE_TRISTE) {
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

    private void atualizarSaude() {

        if (this.energia <= ENERGIA_MORTA || this.saciedade <= SACIEDADE_MORTA || this.felicidade <= FELICIDADE_MORTA) {
            this.saude = ESTADO_SAUDE_MORTA;
            this.vivo = false;

            return;
        }

        this.saude = (this.energia + this.saciedade + this.felicidade) / 3;
    }

    protected boolean sePodeTreinar() {
        return this.estaViva() && this.saude > SAUDE_TREINAR && this.energia >= ENERGIA_TREINAR && this.saciedade >= SACIEDADE_TREINAR;
    }

    protected boolean sePodeExplorar() {
        return this.estaViva() && this.saude > SAUDE_EXPLORAR && this.energia >= ENERGIA_EXPLORAR && this.saciedade >= SACIEDADE_EXPLORAR;
    }

    protected boolean sePodeBrincar() {
        return this.estaViva() && this.saude > SAUDE_BRINCAR && this.energia >= ENERGIA_BRINCAR && this.saciedade >= SACIEDADE_BRINCAR;
    }

    protected boolean sePodeDescansar() {
        return this.estaViva() && this.saude > SAUDE_DESCANSAR && this.energia < ENERGIA_DESCANSAR && this.ultimaAtividade != Atividade.DESCANSAR;
    }

    protected boolean sePodeParticiparDesafio() {
        return this.estaViva() && this.saude > SAUDE_DESAFIAR && this.nivel >= NIVEL_MIN_DESAFIAR && this.nivel % NIVEL_DESAFIAR == 0 && this.energia >= ENERGIA_DESAFIAR && this.saciedade >= SACIEDADE_DESAFIAR && !this.desafiosParticipados.contains(nivel);
    }

    protected boolean aceitaAlimento(TipoAlimento tipoAlimento) {
        return this.getAlimentosCompativeis().contains(tipoAlimento);
    }

    protected boolean sePodeAlimentar(TipoAlimento tipoAlimento) {
        return this.estaViva() && this.saude > SAUDE_ALIMENTAR && this.saciedade < SACIEDADE_ALIMENTAR && aceitaAlimento(tipoAlimento);
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
        return Math.min(this.nivel / MODIFICADOR, LIM_MODIFICADOR);
    }

    protected int calcularConsumoAtributo(int valorBase) {
        return valorBase + calcularModificadorNivel();
    }

    protected int calcularGanhoAtributo(int valorBase) {
        return valorBase - calcularModificadorNivel();
    }

    private int limitarAtributo(int valor) {
        return Math.max(0, Math.min(valor, 100));
    }

    protected void consumirEnergia(int valorBase) {
        this.energia -= limitarAtributo(calcularConsumoAtributo(valorBase));
        this.atualizarSaude();
    }

    protected void ganharEnergia(int quantidade) {
        this.energia += limitarAtributo(quantidade);
        this.atualizarSaude();
    }

    protected void consumirSaciedade(int valorBase) {
        this.saciedade -= limitarAtributo(calcularConsumoAtributo(valorBase));
        this.atualizarSaude();
    }

    protected void ganharSaciedade(int quantidade) {
        this.saciedade += limitarAtributo(quantidade);
        this.atualizarSaude();
    }

    protected void consumirFelicidade(int quantidade) {
        this.felicidade -= limitarAtributo(quantidade);
        this.atualizarSaude();
    }

    protected void ganharFelicidade(int valorBase) {
        this.felicidade += limitarAtributo(calcularGanhoAtributo(valorBase));
        this.atualizarSaude();
    }

    protected void ganharExperiencia(int valorBase) {
        this.experiencia += calcularGanhoAtributo(valorBase);

        while (this.experiencia >= MAX_EXPERIENCIA) {
            this.experiencia -= MAX_EXPERIENCIA;
            this.nivel++;

            this.evoluir();
        }
    }

    public void aplicarDesgasteNatural() {
        this.energia -= desgasteEnergia();
        this.saciedade -= desgasteSaciedade();
        this.felicidade -= desgasteFelicidade();

        this.atualizarSaude();
    }

    public abstract Set<TipoAlimento> getAlimentosCompativeis();

    public abstract void treinar();

    public abstract void explorar();

    public abstract void brincar();

    public abstract void descansar();

    public abstract void participarDesafio();

    public abstract void executarHabilidadeEspecial();

    protected abstract void evoluir();

    protected abstract int desgasteEnergia();

    protected abstract int desgasteSaciedade();

    protected abstract int desgasteFelicidade();

}
