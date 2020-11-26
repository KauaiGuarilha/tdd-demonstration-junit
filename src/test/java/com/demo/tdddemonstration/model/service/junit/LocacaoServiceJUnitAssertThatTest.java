package com.demo.tdddemonstration.model.service.junit;

import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class LocacaoServiceJUnitAssertThatTest {

    @Test
    public void assertThat() throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        //Verificação
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }

    @Test
    public void assertThat2() throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        //Verificação
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }

    @Test
    public void assertThat3() throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        //Verificação
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0)));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
    }


    @Test
    public void assertThatNegative() throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        //Verificação
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(4.0)));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.not(false));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.not(false));
    }
}
