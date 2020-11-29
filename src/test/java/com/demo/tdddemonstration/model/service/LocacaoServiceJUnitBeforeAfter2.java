package com.demo.tdddemonstration.model.service;

import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.junit.*;

import java.util.Date;

public class LocacaoServiceJUnitBeforeAfter2 {

    /*
     Toda vez que a classe recebe uma instância ou variável, o JUnit reinicializa a variável por método,
     para manter o valor, basta criar uma variável de forma static.
    */

    private LocacaoService service;

    @Before
    public void beforeSetup() {
        service = new LocacaoService();
    }

    @BeforeClass
    public static void beforeClassSetup(){
        LocacaoService service = new LocacaoService();

        System.out.println("BeforeClass: Será executado antes de todos os métodos de teste.");
    }

    @AfterClass
    public static void afterClassSetup(){
        System.out.println("AfterClass: Será executado depois de todos os métodos de teste.");
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
