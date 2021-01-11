package com.demo.tdddemonstration.model.service;

import static com.demo.tdddemonstration.utils.DataUtils.adicionarDias;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.utils.DataUtils;

import java.util.Date;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {

        if (filme == null){
            throw new LocadoraException("Filme vazio");
        }

        if(usuario == null){
            throw new LocadoraException("Usuário vazio");
        }

        if(filme.getEstoque() == 0){
            throw new FilmeSemEstoqueException();
        }

        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        return locacao;
    }

    public static void main(String[] args) throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        
        //Verificação
        System.out.println(locacao.getValor() == 5.0);
        System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}
