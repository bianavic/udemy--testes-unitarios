package br.ce.waquino.builders;

import br.ce.wcaquino.entidades.Filme;

// CHAINING METHODS = padrao que permite o encadeamento entre metodos
public class FilmeBuilder {

    private Filme filme;
    private FilmeBuilder() {}

    public static FilmeBuilder umFilme() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme();
        builder.filme.setEstoque(2);
        builder.filme.setNome("Filme 1");
        builder.filme.setPrecoLocacao(4.0);
        return builder;
    }

    // criacao de um builder especifico para nao precisar fazer varias chamadas
    public static FilmeBuilder umFilmeSemEstoque() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme();
        builder.filme.setEstoque(0);
        builder.filme.setNome("Filme 1");
        builder.filme.setPrecoLocacao(4.0);
        return builder;
    }

    // criar um METODOS para o atributo que quero alterar (estoque)
    public FilmeBuilder semEstoque() {
        filme.setEstoque(0);
        // como estou num metodo de instancia, retorno a propria instancia
        return this;
    }

    public FilmeBuilder comValor(Double valor) {
        filme.setPrecoLocacao(valor);
        return this;
    }

    public Filme agora() {
        return filme;
    }
}
