package com.demo.tdddemonstration.model.service;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

public class LocacaoServiceJUnitExceptionTest {

    /* Forma Elegante de esperar uma Exceção específica */

    @Test(expected = FilmeSemEstoqueException.class)
    public void deveriaEstourarUmaExcecaoAoValidarEstoque() throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);
    }

    /* Forma Robusta de esperar uma Exceção específica */

    @Test
    public void deveriaEstourarUmaExcecaoAoValidarUsuarioNull() throws FilmeSemEstoqueException {

        //Cenário
        LocacaoService service = new LocacaoService();
        Filme filme = new Filme("Filme 1", 2, 5.0);

        try {
            //Ação
            Locacao locacao = service.alugarFilme(null, filme);
            Assert.fail();

        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuário vazio"));
        }
    }

    /* Forma Nova de esperar uma Exceção específica */

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deveriaEstourarUmaExcecaoAoValidarFilmeNull() throws LocadoraException, FilmeSemEstoqueException {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");

        //Verificação
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        //Ação
        Locacao locacao = service.alugarFilme(usuario, null);
    }

     /*
     @Rules serve pra validar todas as verificações, independente da primeira verificação dar erro, ele lerá todas
     e marcar quais estão com erro.
    */

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void testeLocacao() throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);

        //Verificação
        error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
    }
}
