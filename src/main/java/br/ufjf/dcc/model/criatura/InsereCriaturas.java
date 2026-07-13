package br.ufjf.dcc.model.criatura;

public class InsereCriaturas {

    public static Criatura criar(String especie, String nome, int idade, int nivel, int experiencia, int energia, int saciedade, int felicidade) {
        return switch (especie) {

            case "Aquari" ->
                new Aquari(nome, idade, nivel, experiencia, energia, saciedade, felicidade);

            case "Draconis" ->
                new Draconis(nome, idade, nivel, experiencia, energia, saciedade, felicidade);

            case "DraconisCelestial" ->
                new DraconisCelestial(nome, idade, nivel, experiencia, energia, saciedade, felicidade);

            case "Fungari" ->
                new Fungari(nome, idade, nivel, experiencia, energia, saciedade, felicidade);

            case "Lumini" ->
                new Lumini(nome, idade, nivel, experiencia, energia, saciedade, felicidade);

            case "Mecanis" ->
                new Mecanis(nome, idade, nivel, experiencia, energia, saciedade, felicidade);

            default ->
                throw new IllegalArgumentException("Espécie inválida: " + especie);
        };
    }

    public static Criatura criar(String especie, String nome, int idade) {
        return switch (especie) {

            case "Aquari" ->
                new Aquari(nome, idade);

            case "Draconis" ->
                new Draconis(nome, idade);

            case "DraconisCelestial" ->
                new DraconisCelestial(nome, idade);

            case "Fungari" ->
                new Fungari(nome, idade);

            case "Lumini" ->
                new Lumini(nome, idade);

            case "Mecanis" ->
                new Mecanis(nome, idade);

            default ->
                throw new IllegalArgumentException("Espécie inválida: " + especie);
        };
    }
}
