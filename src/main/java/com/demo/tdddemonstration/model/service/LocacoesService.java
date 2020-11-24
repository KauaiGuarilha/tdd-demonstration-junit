package com.demo.tdddemonstration.model.service;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;

import java.util.Date;
import java.util.List;

import static com.demo.tdddemonstration.utils.DataUtils.adicionarDias;

public class LocacoesService {

    public Locacao alugarFilmes(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {

        if(filmes == null || filmes.isEmpty()){
            throw new LocadoraException("Filme vazio");
        }

        if(usuario == null){
            throw new LocadoraException("Usuário vazio");
        }

        for(Filme filme : filmes){

            if(filme.getEstoque() == 0){
                throw  new FilmeSemEstoqueException();
                //Sempre que chamo uma exception específica, estou garantido que o problema é único para essa situação
                // E não de forma genérica
            }
        }


        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());

        Double valorTotal = 0d;
        for(Filme filme : filmes){

            valorTotal += filme.getPrecoLocacao();
        }
        locacao.setValor(valorTotal);

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }
}
