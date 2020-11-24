package com.demo.tdddemonstration.examplesJUnit.Assert;

import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacoesService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AssertTest2 {

    @Test
    public void testeComVerificacaoComum() {

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        Locacao locacao = null;
        try {
            locacao = services.alugarFilmes(usuario, filmes);

            //Verificação
            System.out.println(locacao.getValor() == 5.0);
            System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
            System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testesComAsserts() {

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        Locacao locacao = null;
        try {
            locacao = services.alugarFilmes(usuario, filmes);

            //Verificação
            //Como os retornos são Booleanos, no caso True or False, utilizamos o Assert.assertTrue
            Assert.assertTrue(locacao.getValor() == 5.0);
            Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
            Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testesComAsserts2() {

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        Locacao locacao = null;
        try {
            locacao = services.alugarFilmes(usuario, filmes);

            //Verificação
            //Como os retornos são Booleanos, no caso True or False, utilizamos o Assert.assertTrue
            Assert.assertEquals(5.0, locacao.getValor(), 0.1);
            Assert.assertNotNull(locacao.getDataLocacao());
            Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
            Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
