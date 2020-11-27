package com.demo.tdddemonstration.model.service;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.utils.DataUtils;

import java.util.Calendar;
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
                /*
                 Sempre que chamo uma exception específica, estou garantido que o problema é único para essa situação
                 e não de forma genérica
                */
            }
        }

        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());

        Double valorTotal = 0d;
        for(int i = 0; i < filmes.size(); i++){
            Filme filme = filmes.get(i);
            Double valorFilme = filme.getPrecoLocacao();

            switch (i) {
                case 2 : valorFilme = valorFilme * 0.75; break;
                case 3 : valorFilme = valorFilme * 0.5; break;
                case 4 : valorFilme = valorFilme * 0.25; break;
                case 5 : valorFilme = 0d; break;
            }
            valorTotal += valorFilme;
        }
        locacao.setValor(valorTotal);

        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);

        if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
            dataEntrega = adicionarDias(dataEntrega, 1);
        }
        locacao.setDataRetorno(dataEntrega);

        return locacao;
    }
}
