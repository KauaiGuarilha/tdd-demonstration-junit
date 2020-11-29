package com.demo.tdddemonstration.model.service;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacoesService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class LocacoesServiceJUnitDesafioTest {

    private LocacoesService service;

    @Before
    public void setup(){
        service = new LocacoesService();
    }

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void testeLocacoes() throws Exception {

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filme = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        Locacao locacao = service.alugarFilmes(usuario, filme);

        //Verificação
        error.checkThat(locacao.getValor(), is(CoreMatchers.equalTo(5.0)));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void deveriaOcorrerAExceptionFilmeSemEstoqueException() throws Exception{

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4.0));

        //Ação
        service.alugarFilmes(usuario, filmes);
    }

    @Test
    public void deveriaOcorrerAExceptionLocadoraException() throws FilmeSemEstoqueException{

        //Cenário
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        //Ação
        try {
            service.alugarFilmes(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), is("Usuário vazio"));
        }
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deveriaOcorrerAExceptionExpectedException() throws FilmeSemEstoqueException, LocadoraException{

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");

        //Verificação
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        //Ação
        service.alugarFilmes(usuario, null);
    }
}
