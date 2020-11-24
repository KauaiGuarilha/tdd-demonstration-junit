package com.demo.tdddemonstration.examplesJUnit.Assert;

import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacoesService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AssertRulesThat {

    /*
     @Rule com o ErrorCollector, faz com que valide todos os Assertivos, ao invés de parar no primeiro
     Logo, ele irá validar todos, independente se tiver erro no primeiro Assert
    */
    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void testesComAssertsRules3(){

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

        //Ação
        Locacao locacao = null;
        try {
            locacao = services.alugarFilmes(usuario, filmes);

            //verificacao
            error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
            error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
            error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));

        } catch (Exception e) {
            e.printStackTrace();

            Assert.fail("Não deveria lançar exceção"); //Mostrar Falha
        }
    }

    /*
     @Rule com o ErrorCollector, faz com que valide todos os Assertivos, ao invés de parar no primeiro
     Logo, ele irá validar todos, independente se tiver erro no primeiro Assert
    */
    @Rule
    public ErrorCollector error1 = new ErrorCollector();

    @Test
    public void testesComAssertsRules4() throws Exception {

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

        //Ação
        Locacao locacao = null;

        locacao = services.alugarFilmes(usuario, filmes);

        //Verificação
        error1.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
        error1.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        error1.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
    }
}
