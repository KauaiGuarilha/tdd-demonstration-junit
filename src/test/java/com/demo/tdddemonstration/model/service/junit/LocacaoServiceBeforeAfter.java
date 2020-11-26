package com.demo.tdddemonstration.model.service.junit;

import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class LocacaoServiceBeforeAfter {

    private LocacaoService service;

    /*
     Before: Geralmente utilizado para iniciar instâncias que se repetem, porém precisam ser globais
     After: Geralmente utilizado para
    */

    @Before
    public void beforeSetup(){
        service = new LocacaoService();

        System.out.println("Before: Será executado antes de cada método de teste.");
    }

    @After
    public void afterSetup(){
        System.out.println("After: Será executado depois de cada método de teste.");
    }

    @Test
    public void testeLocacao() throws Exception {

        // Cenário
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        System.out.println("Teste de locação");

        // Ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        // Verificação
        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }

    @Test
    public void testeLocacao2() throws Exception {

        // Cenário
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        System.out.println("Teste de locação");

        // Ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        // Verificação
        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}
