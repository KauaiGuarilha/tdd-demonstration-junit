package com.demo.tdddemonstration.builders;

import com.demo.tdddemonstration.model.entity.Filme;

public class FilmeBuilder {

    private Filme filme;

    private FilmeBuilder(){}

    public static FilmeBuilder umFilme(){

        FilmeBuilder builder = new FilmeBuilder();

        builder.filme = new Filme();
        builder.filme.setEstoque(2);
        builder.filme.setNome("Filme 1");
        builder.filme.setPrecoLocacao(5.0);

        return builder;
    }

    /* Existem teste que precisam do estoque zerado. Método pra que seja utilizado no mesmo */
    public FilmeBuilder semEstoque(){
        filme.setEstoque(0);
        return this;
    }

    public Filme agora(){
        return filme;
    }
}
