package com.demo.tdddemonstration.model.service.junit;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.utils.matchers.DiaSemanaMatcher;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacoesService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.demo.tdddemonstration.utils.matchers.MatchersProprios.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocacoesServiceJUnitMarchersTest {

    private LocacoesService service;

    @Before
    public void setup(){
        service = new LocacoesService();
    }

    @Test
    public void deveriaDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        Assert.assertThat(resultado.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
    }

    @Test
    public void deveriaDevolverNaSegundaAoAlugarNoSabado2() throws FilmeSemEstoqueException, LocadoraException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        Assert.assertThat(resultado.getDataRetorno(), caiEm(Calendar.MONDAY));
    }

    @Test
    public void deveriaDevolverNaSegundaAoAlugarNoSabado3() throws FilmeSemEstoqueException, LocadoraException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        Assert.assertThat(resultado.getDataRetorno(), caiNumaSegunda());
    }

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void deveriaVerificarDiferencaDeDias() throws FilmeSemEstoqueException, LocadoraException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        error.checkThat(resultado.getDataRetorno(), hojeComDiferencaDias(2));
    }

    @Test
    public void deveriaVerificarALocacaoHoje() throws FilmeSemEstoqueException, LocadoraException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        error.checkThat(resultado.getDataLocacao(), hoje());
    }
}
