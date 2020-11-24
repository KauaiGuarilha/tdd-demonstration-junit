package com.demo.tdddemonstration.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Locacao {

    private Usuario usuario;
    private Filme filme;
    private List<Filme> filmes;
    private Date dataLocacao;
    private Date dataRetorno;
    private Double valor;
}
