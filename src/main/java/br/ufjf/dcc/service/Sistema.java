package br.ufjf.dcc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.ufjf.dcc.model.alimento.Estoque;
import br.ufjf.dcc.model.alimento.Reposicao;
import br.ufjf.dcc.model.criatura.Criatura;
import br.ufjf.dcc.model.criatura.DraconisCelestial;
import br.ufjf.dcc.model.criatura.InsereCriaturas;
import br.ufjf.dcc.model.criatura.ListaCriaturas;
import br.ufjf.dcc.model.enums.TipoAlimento;
import br.ufjf.dcc.model.estatistica.Estatistica;
import br.ufjf.dcc.model.tempo.PassagemTempo;
import br.ufjf.dcc.util.csv.ConversorEstatisticaCSV;
import br.ufjf.dcc.util.csv.EscritorCSV;
import br.ufjf.dcc.util.json.ConversorCriaturaJSON;
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

    private void exibirMenu() {
        System.out.println("MENU");
        System.out.println("1. Criar Criatura");
        System.out.println("2. Remover Criatura");
        System.out.println("3. Editar Criatura");
        System.out.println("4. Exibir Informações da Criatura");
        System.out.println("5. Exibir Status Completo");
        System.out.println("6. Exibir Criaturas Cadastrados");
        System.out.println("7. Alimentar Criatura");
        System.out.println("8. Brincar com Criatura");
        System.out.println("9. Colocar Criatura para Dormir");
        System.out.println("10. Treinar Criatura");
        System.out.println("11. Participar de Desafio");
        System.out.println("12. Executar habilidade especial");
        System.out.println("13. Imprimir estatísticas");
        System.out.println("14. Exportar estatísticas (CSV)");
        System.out.println("15. Importar Criaturas (JSON)");
        System.out.println("16. Exportar Criaturas (JSON)");
        System.out.println("17. Encerrar Sistema");
    }

    public void iniciar() {
        while (executando) {

            exibirMenu();

            int acao = lerInteiro("Escolha uma opção: ");

            switch (acao) {

                case 1 ->
                    criarCriatura();

                case 2 ->
                    removerCriatura();

                case 3 ->
                    editarCriatura();

                case 4 ->
                    exibirInfosCriatura();

                case 5 ->
                    exibirStatusCompleto();

                case 6 ->
                    exibirCriaturas();

                case 7 ->
                    alimentarCriatura();

                case 8 ->
                    brincarCriatura();

                case 9 ->
                    dormirCriatura();

                case 10 ->
                    treinarCriatura();

                case 11 ->
                    participarDesafio();

                case 12 ->
                    habilidadeEspecial();

                case 13 ->
                    imprimirEstatisticas();

                case 14 ->
                    exportarEstatisticas();

                case 15 ->
                    importarCriaturas();

                case 16 ->
                    exportarCriaturas();

                case 17 ->
                    encerrarSistema();

                default ->
                    System.out.println("Opção inválida.");
            }
        }
    }

    private Criatura selecionarCriatura() {
        if (listaCriaturas.getQteTotalCriaturas() == 0) {

            System.out.println("Nenhuma criatura cadastrada.");

            return null;
        }

        listaCriaturas.exibirCriaturas();

        int opcao = lerInteiro("Escolha uma opção: ");

        if (opcao == 0) {
            return null;
        }

        if (opcao < 1 || opcao > listaCriaturas.getQteTotalCriaturas()) {

            System.out.println("Criatura inválida.");

            return null;
        }

        return listaCriaturas.getCriaturas().get(opcao - 1);
    }

    private void avancarTempo() {
        tempo.registrarAtividade(listaCriaturas.getCriaturas());
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);

                int valor = leitor.nextInt();
                leitor.nextLine();

                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                leitor.nextLine();
            }
        }
    }

    private String lerTexto(String mensagem) {
        String texto;

        do {
            System.out.print(mensagem);

            texto = leitor.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("Texto inválido.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private String lerEspecie() {
        while (true) {
            System.out.println("Escolha a espécie: ");
            System.out.println("0. Voltar");
            System.out.println("1. Aquari");
            System.out.println("2. Draconis");
            System.out.println("3. Draconis Celestial");
            System.out.println("4. Fungari");
            System.out.println("5. Lumini");
            System.out.println("6. Mecanis");

            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 0 -> {
                    return null;
                }

                case 1 -> {
                    return "Aquari";
                }

                case 2 -> {
                    return "Draconis";
                }

                case 3 -> {
                    return "DraconisCelestial";
                }

                case 4 -> {
                    return "Fungari";
                }

                case 5 -> {
                    return "Lumini";
                }

                case 6 -> {
                    return "Mecanis";
                }

                default ->
                    System.out.println("Espécie inválida.");
            }
        }
    }

    private void criarCriatura() {
        System.out.println("Como deseja criar a criatura?");
        System.out.println("0. Voltar");
        System.out.println("1. Criatura com atributos pré-definidos");
        System.out.println("2. Informar atributos");

        int opcao = lerInteiro("Escolha uma opção: ");

        if (opcao != 0) {
            String especie = lerEspecie();

            if (especie != null) {
                String nome = lerTexto("Nome: ");
                int idade = lerInteiro("Idade: ");

                try {
                    Criatura criatura;

                    switch (opcao) {
                        case 1 -> {

                            criatura = InsereCriaturas.criar(especie, nome, idade);

                        }

                        case 2 -> {

                            int nivel = lerInteiro("Nível: ");
                            int experiencia = lerInteiro("Experiência: ");
                            int energia = lerInteiro("Energia: ");
                            int saciedade = lerInteiro("Saciedade: ");
                            int felicidade = lerInteiro("Felicidade: ");

                            criatura = InsereCriaturas.criar(
                                    especie,
                                    nome,
                                    idade,
                                    nivel,
                                    experiencia,
                                    energia,
                                    saciedade,
                                    felicidade
                            );

                        }
                        default -> {
                            System.out.println("Opção inválida.");
                            return;
                        }
                    }
                    listaCriaturas.criarCriatura(criatura);

                    System.out.println("Criatura criada com sucesso.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void removerCriatura() {
        Criatura criatura = selecionarCriatura();

        if (criatura != null) {
            listaCriaturas.removerCriatura(criatura);

            System.out.println("Criatura removida.");
        }
    }

    private void editarCriatura() {
        Criatura criatura = selecionarCriatura();

        if (criatura != null) {
            System.out.println("Escolha o atributo a ser editado:");
            System.out.println("1. Nome");
            System.out.println("2. Idade");
            System.out.println("3. Nível");
            System.out.println("4. Experiência");
            System.out.println("5. Energia");
            System.out.println("6. Saciedade");
            System.out.println("7. Felicidade");
            System.out.println("8. Todos os atributos");

            int opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {

                    case 1 ->
                        criatura.setNome(lerTexto("Nome: "));

                    case 2 ->
                        criatura.setIdade(lerInteiro("Idade: "));

                    case 3 ->
                        criatura.setNivel(lerInteiro("Nível: "));

                    case 4 ->
                        criatura.setExperiencia(lerInteiro("Experiência: "));

                    case 5 ->
                        criatura.setEnergia(lerInteiro("Energia: "));

                    case 6 ->
                        criatura.setSaciedade(lerInteiro("Saciedade: "));

                    case 7 ->
                        criatura.setFelicidade(lerInteiro("Felicidade: "));

                    case 8 -> {

                        String nome = lerTexto("Nome: ");
                        int idade = lerInteiro("Idade: ");
                        int nivel = lerInteiro("Nível: ");
                        int experiencia = lerInteiro("Experiência: ");
                        int energia = lerInteiro("Energia: ");
                        int saciedade = lerInteiro("Saciedade: ");
                        int felicidade = lerInteiro("Felicidade: ");

                        criatura.setNome(nome);
                        criatura.setIdade(idade);
                        criatura.setNivel(nivel);
                        criatura.setExperiencia(experiencia);
                        criatura.setEnergia(energia);
                        criatura.setSaciedade(saciedade);
                        criatura.setFelicidade(felicidade);
                    }

                    default -> {
                        System.out.println("Opção inválida.");
                        return;
                    }
                }

                System.out.println("Atributo atualizado com sucesso.");

            } catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());
            }
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

    private void exibirCriaturas() {
        listaCriaturas.exibirCriaturas();
    }

    private void alimentarCriatura() {
        Criatura criatura = selecionarCriatura();

        if (criatura != null) {
            List<TipoAlimento> alimentos = new ArrayList<>(criatura.getAlimentosCompativeis());

            System.out.println("Alimentos disponíveis:");

            for (int i = 0; i < alimentos.size(); i++) {
                TipoAlimento alimento = alimentos.get(i);
                System.out.println((i + 1) + ". " + alimento.getDescricao()
                        + " (" + estoque.getQteDisponivelAlimento(alimento) + " unidades)");
            }

            int opcao = lerInteiro("Escolha um alimento: ");

            if (opcao < 1 || opcao > alimentos.size()) {
                System.out.println("Alimento inválido.");
                return;
            }

            TipoAlimento alimentoEscolhido = alimentos.get(opcao - 1);

            int saciedadeAntes = criatura.getSaciedade();

            try {
                criatura.alimentar(alimentoEscolhido, estoque);

                System.out.println(criatura.getNome() + " foi alimentada com "
                        + alimentoEscolhido.getDescricao()
                        + ". Sua saciedade antes era " + saciedadeAntes
                        + " e agora é " + criatura.getSaciedade() + ".");

                reposicao.executar();
                avancarTempo();

            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void brincarCriatura() {
        Criatura criatura = selecionarCriatura();

        if (criatura != null) {
            int felicidadeAntes = criatura.getFelicidade();
            int energiaAntes = criatura.getEnergia();
            int saciedadeAntes = criatura.getSaciedade();

            try {
                criatura.brincar();

                System.out.println(criatura.getNome() + " brincou. Sua felicidade, energia e saciedade, antes eram "
                        + felicidadeAntes + ", " + energiaAntes + " e " + saciedadeAntes
                        + ", respectivamente, e agora são "
                        + criatura.getFelicidade() + ", "
                        + criatura.getEnergia() + " e "
                        + criatura.getSaciedade() + ".");

                avancarTempo();
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void dormirCriatura() {
        Criatura criatura = selecionarCriatura();

        if (criatura != null) {
            int energiaAntes = criatura.getEnergia();
            try {
                criatura.descansar();

                System.out.println(criatura.getNome() + " dormiu. Sua energia antes era " + energiaAntes + " e agora é " + criatura.getEnergia() + ".");

                avancarTempo();
            } catch (IllegalStateException e) {

                System.out.println(e.getMessage());

            }
        }
    }

    private void treinarCriatura() {
        Criatura criatura = selecionarCriatura();

        if (criatura != null) {
            int experienciaAntes = criatura.getExperiencia();
            int energiaAntes = criatura.getEnergia();
            int saciedadeAntes = criatura.getSaciedade();

            try {
                criatura.treinar();

                System.out.println(criatura.getNome() + " treinou. Sua expêriencia, energia e saciedade, antes eram " + experienciaAntes + ", " + energiaAntes + " e " + saciedadeAntes + ", respectivamente, e agora são " + criatura.getExperiencia() + ", " + criatura.getEnergia() + " e " + criatura.getSaciedade() + ".");

                avancarTempo();
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());

            }
        }
    }

    private void participarDesafio() {
        Criatura criatura = selecionarCriatura();

        if (criatura != null) {
            int experienciaAntes = criatura.getExperiencia();
            int energiaAntes = criatura.getEnergia();
            int saciedadeAntes = criatura.getSaciedade();
            int felicidadeAntes = criatura.getFelicidade();

            try {
                criatura.participarDesafio();

                System.out.println(criatura.getNome() + " participou de um desafio. Sua expêriencia, energia, saciedade e felicidade, antes eram " + experienciaAntes + ", " + energiaAntes + ", " + saciedadeAntes + " e " + felicidadeAntes + ", respectivamente, e agora são " + criatura.getExperiencia() + ", " + criatura.getEnergia() + ", " + criatura.getSaciedade() + " e " + criatura.getFelicidade() + ".");

                avancarTempo();
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void habilidadeEspecial() {
        Criatura criatura = selecionarCriatura();

        if (criatura != null) {
            try {
                if (criatura instanceof DraconisCelestial draconisCelestial) {

                    System.out.println("Escolha a habilidade:");
                    System.out.println("1. Realizar voo");
                    System.out.println("2. Estudar");
                    System.out.println("3. Ambas");

                    int opcao = lerInteiro("Escolha uma opção: ");

                    draconisCelestial.executarHabilidadeEspecial(opcao);

                } else {

                    criatura.executarHabilidadeEspecial();
                }

                avancarTempo();
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void imprimirEstatisticas() {
        Estatistica estatisticas = new Estatistica(listaCriaturas, tempo, estoque);
        estatisticas.imprimirEstatisticas();
    }

    private void exportarEstatisticas() {
        String caminho = lerTexto("Digite o caminho do arquivo CSV (0 para voltar): ");

        if (caminho.equals("0")) {
            return;
        }

        Estatistica estatistica = new Estatistica(listaCriaturas, tempo, estoque);

        try {
            EscritorCSV.salvar(caminho, estatistica.gerarLinhasCSV(), new ConversorEstatisticaCSV(), "nome;valor"
            );

            System.out.println("Estatísticas exportadas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao exportar estatísticas.");
        }
    }

    private void importarCriaturas() {
        String caminho = lerTexto("Digite o caminho do arquivo JSON (0 para voltar): ");

        if (caminho.equals("0")) {
            return;
        }

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
        String caminho = lerTexto("Digite o caminho do arquivo CSV (0 para voltar): ");

        if (caminho.equals("0")) {
            return;
        }

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
