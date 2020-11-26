package com.demo.tdddemonstration.model.service.junit;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

public class LocacaoServiceJUnitExceptionTest {

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

    /* Forma Elegante de esperar uma Exceção */

    @Test(expected = Exception.class)
    public void deveriaEstourarUmaExcecaoAoValidarEstoque() throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);
    }

    /* Forma Robusta de esperar uma Exceção

    @Test
    public void deveriaEstourarUmaExcecaoAoValidarEstoque3() {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        try {
            //Ação
            Locacao locacao = service.alugarFilme(usuario, filme);
            Assert.fail("Deveria ter lançado uma exceção");
        } catch (Exception e){
            Assert.assertThat(e.getMessage() , CoreMatchers.is("Filme sem estoque"));
        }
    }*/

    /* Forma Nova de esperar uma Exceção

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deveriaEstourarUmaExcecaoAoValidarEstoque4() throws Exception {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //Verificação
        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        //Ação
        Locacao locacao = service.alugarFilme(usuario, filme);
    }*/
}
