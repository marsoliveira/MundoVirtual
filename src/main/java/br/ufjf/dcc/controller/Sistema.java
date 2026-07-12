package br.ufjf.dcc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufjf.dcc.model.alimento.Estoque;
import br.ufjf.dcc.model.alimento.Reposicao;
import br.ufjf.dcc.model.criatura.ConversorCriaturaJSON;
import br.ufjf.dcc.model.criatura.Criatura;
import br.ufjf.dcc.model.criatura.InsereCriaturas;
import br.ufjf.dcc.model.criatura.ListaCriaturas;
import br.ufjf.dcc.model.enums.TipoAlimento;
import br.ufjf.dcc.model.estatistica.ConversorEstatisticaCSV;
import br.ufjf.dcc.model.estatistica.Estatistica;
import br.ufjf.dcc.model.tempo.PassagemTempo;
import br.ufjf.dcc.util.csv.EscritorCSV;
import br.ufjf.dcc.util.json.EscritorJSON;
import br.ufjf.dcc.util.json.LeitorJSON;

public class Sistema {

    private Scanner leitor;

    private boolean executando;

    private ListaCriaturas listaCriaturas;
    private PassagemTempo tempo;
    private Estoque estoque;
    private Reposicao reposicao;

    public Sistema(ListaCriaturas listaCriaturas, Estoque estoque) {

        this.leitor = new Scanner(System.in);

        this.executando = true;

        this.listaCriaturas = listaCriaturas;
        this.tempo = new PassagemTempo();
        this.estoque = estoque;
        this.reposicao = new Reposicao(estoque, listaCriaturas);
    }

    public void iniciar() {

        while (executando) {

            exibirMenu();

            System.out.println("Escolha uma opção: ");

            int acao = leitor.nextInt();

            switch (acao) {

                case 1 ->
                    criarCriatura();

                case 2 ->
                    exibirCriaturas();

                case 3 ->
                    removerCriatura();

                case 4 ->
                    exibirInfosCriatura();

                case 5 ->
                    exibirStatusCompleto();

                case 6 ->
                    alimentarCriatura();

                case 7 ->
                    brincarCriatura();

                case 8 ->
                    dormirCriatura();

                case 9 ->
                    treinarCriatura();

                case 10 ->
                    participarDesafio();

                case 11 ->
                    habilidadeEspecial();

                case 12 ->
                    imprimirEstatisticas();

                case 13 ->
                    exportarEstatisticas();

                case 14 ->
                    importarCriaturas();

                case 15 ->
                    exportarCriaturas();

                case 16 ->
                    encerrarSistema();

                default ->
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void exibirMenu() {

        System.out.println("MENU");
        System.out.println("1 - Criar Criatura");
        System.out.println("2 - Exibir Criaturas Cadastrados");
        System.out.println("3 - Remover Criatura");
        System.out.println("4 - Exibir Informações do Criatura");
        System.out.println("5 - Exibir Status Completo");
        System.out.println("6 - Alimentar Criatura");
        System.out.println("7 - Brincar com Criatura");
        System.out.println("8 - Colocar Criatura para Dormir");
        System.out.println("9 - Treinar Criatura");
        System.out.println("10 - Participar de Desafio");
        System.out.println("11 - Executar habilidade especial");
        System.out.println("12 - Imprimir estatísticas");
        System.out.println("13 - Exportar estatísticas (CSV)");
        System.out.println("14 - Importar Criaturas (JSON)");
        System.out.println("15 - Exportar Criaturas (JSON)");
        System.out.println("16 - Encerrar Sistema");

    }

    private Criatura selecionarCriatura() {

        if (listaCriaturas.getQteTotalCriaturas() == 0) {

            System.out.println("Nenhuma criatura cadastrada.");

            return null;
        }

        listaCriaturas.exibeCriaturas();

        System.out.println("Escolha uma criatura: ");

        int acao = leitor.nextInt();

        if (acao < 1 || acao > listaCriaturas.getQteTotalCriaturas()) {

            System.out.println("Criatura inválida.");

            return null;
        }

        return listaCriaturas.getCriaturas().get(acao - 1);
    }

    private void avancarTempo() {

        tempo.registrarAtividade(listaCriaturas.getCriaturas());
    }

    private void criarCriatura() {

        leitor.nextLine();

        System.out.println("Nome: ");
        String nome = leitor.nextLine();

        System.out.println("Escolha a espécie: ");
        System.out.println("1 - Aquari");
        System.out.println("2 - Draconis");
        System.out.println("3 - Draconis Celestial");
        System.out.println("4 - Fungari");
        System.out.println("5 - Lumini");
        System.out.println("6 - Mecanis");

        int opcao = leitor.nextInt();

        String especie;

        switch (opcao) {

            case 1 ->
                especie = "Aquari";

            case 2 ->
                especie = "Draconis";

            case 3 ->
                especie = "DraconisCelestial";

            case 4 ->
                especie = "Fungari";

            case 5 ->
                especie = "Lumini";

            case 6 ->
                especie = "Mecanis";

            default -> {
                System.out.println("Espécie inválida.");
                return;
            }
        }

        System.out.println("Idade: ");
        int idade = leitor.nextInt();

        System.out.println("Nível: ");
        int nivel = leitor.nextInt();

        System.out.println("Experiência: ");
        int experiencia = leitor.nextInt();

        System.out.println("Energia: ");
        int energia = leitor.nextInt();

        System.out.println("Saciedade: ");
        int saciedade = leitor.nextInt();

        System.out.println("Felicidade: ");
        int felicidade = leitor.nextInt();

        try {

            Criatura criatura = InsereCriaturas.criar(
                    especie,
                    nome,
                    idade,
                    nivel,
                    experiencia,
                    energia,
                    saciedade,
                    felicidade
            );

            listaCriaturas.criarCriatura(criatura);

            System.out.println("Criatura criada com sucesso.");

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }
    }

    private void exibirCriaturas() {

        listaCriaturas.exibeCriaturas();
    }

    private void removerCriatura() {

        Criatura criatura = selecionarCriatura();

        if (criatura != null) {

            listaCriaturas.removerCriatura(criatura);

            System.out.println("Criatura removida.");
        }
    }

    private void exibirInfosCriatura() {

        Criatura criatura = selecionarCriatura();

        if (criatura != null) {

            criatura.exibirInformacoes();
        }
    }

    private void exibirStatusCompleto() {

        Criatura criatura = selecionarCriatura();

        if (criatura != null) {

            criatura.exibirEstadoAtual();
        }
    }

    private void alimentarCriatura() {

        Criatura criatura = selecionarCriatura();

        if (criatura == null) {
            return;
        }

        List<TipoAlimento> alimentos = new ArrayList<>(criatura.getAlimentosCompativeis());

        System.out.println("Alimentos disponíveis:");

        for (int i = 0; i < alimentos.size(); i++) {

            TipoAlimento alimento = alimentos.get(i);

            System.out.println((i + 1) + " - " + alimento.getDescricao() + " (" + estoque.getQteDisponivelAlimento(alimento) + " unidades)");
        }

        System.out.print("Escolha um alimento: ");

        int opcao = leitor.nextInt();

        if (opcao < 1 || opcao > alimentos.size()) {

            System.out.println("Alimento inválido.");
            return;
        }

        TipoAlimento alimentoEscolhido = alimentos.get(opcao - 1);

        criatura.alimentar(alimentoEscolhido, estoque);

        reposicao.executar();

        avancarTempo();
    }

    private void brincarCriatura() {

        Criatura criatura = selecionarCriatura();

        if (criatura == null) {
            return;
        }

        criatura.brincar();

        avancarTempo();
    }

    private void dormirCriatura() {

        Criatura criatura = selecionarCriatura();

        if (criatura == null) {
            return;
        }

        criatura.descansar();

        avancarTempo();
    }

    private void treinarCriatura() {

        Criatura criatura = selecionarCriatura();

        if (criatura == null) {
            return;
        }

        criatura.treinar();

        avancarTempo();
    }

    private void participarDesafio() {

        Criatura criatura = selecionarCriatura();

        if (criatura == null) {
            return;
        }

        criatura.participarDesafio();

        avancarTempo();
    }

    private void habilidadeEspecial() {

        Criatura criatura = selecionarCriatura();

        if (criatura == null) {
            return;
        }

        criatura.executarHabilidadeEspecial();

        avancarTempo();
    }

    private void imprimirEstatisticas() {
        Estatistica estatisticas = new Estatistica(listaCriaturas, tempo, estoque);
        estatisticas.imprimirEstatisticas();
    }

    private void exportarEstatisticas() {

        System.out.print("Digite o caminho do arquivo CSV: ");

        leitor.nextLine();
        String caminho = leitor.nextLine();

        Estatistica estatistica
                = new Estatistica(listaCriaturas, tempo, estoque);

        try {

            EscritorCSV.salvar(caminho, estatistica.gerarLinhasCSV(), new ConversorEstatisticaCSV(), "nome;valor"
            );

            System.out.println("Estatísticas exportadas com sucesso.");

        } catch (IOException e) {

            System.out.println(
                    "Erro ao exportar estatísticas."
            );
        }
    }

    private void importarCriaturas() {

        System.out.print("Digite o caminho do arquivo JSON: ");

        leitor.nextLine();

        String caminho = leitor.nextLine();

        try {
            List<Criatura> criaturasImportadas = LeitorJSON.carregar(caminho, new ConversorCriaturaJSON());

            for (Criatura criatura : criaturasImportadas) {
                listaCriaturas.criarCriatura(criatura);
            }

            System.out.println(criaturasImportadas.size() + " criaturas importadas.");

        } catch (IOException e) {
            System.out.println("Erro ao importar criaturas.");
        }
    }

    private void exportarCriaturas() {
        System.out.print("Digite o caminho do arquivo JSON: ");

        leitor.nextLine();

        String caminho = leitor.nextLine();

        try {

            EscritorJSON.salvar(caminho, listaCriaturas.getCriaturas(), new ConversorCriaturaJSON());

            System.out.println("Criaturas exportadas com sucesso.");

        } catch (IOException e) {

            System.out.println("Erro ao exportar criaturas.");
        }
    }

    private void encerrarSistema() {

        System.out.println("Encerrando sistema...");

        executando = false;

        leitor.close();
    }
}
