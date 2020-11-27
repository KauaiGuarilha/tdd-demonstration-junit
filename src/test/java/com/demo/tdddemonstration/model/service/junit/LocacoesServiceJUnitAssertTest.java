package com.demo.tdddemonstration.model.service.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import com.demo.tdddemonstration.model.service.LocacoesService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.junit.*;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LocacoesServiceJUnitAssertTest {

    private LocacoesService service;

    @Before
    public void setup(){
        service = new LocacoesService();
    }

    @Test
    public void deveriaPagar75PorcentoNoFilme3() throws FilmeSemEstoqueException, LocadoraException {

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        assertThat(resultado.getValor(), is(11.0));
    }


    @Test
    public void deveriaPagar50PorcentoNoFilme4() throws FilmeSemEstoqueException, LocadoraException{

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        assertThat(resultado.getValor(), is(13.0));
    }


    @Test
    public void deveriaPagar25PorcentoNoFilme5() throws FilmeSemEstoqueException, LocadoraException{

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0),
                new Filme("Filme 5", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        assertThat(resultado.getValor(), is(14.0));
    }


    @Test
    public void deveriaPagar0PorcentoNoFilme6() throws FilmeSemEstoqueException, LocadoraException{

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(
                new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0),
                new Filme("Filme 5", 2, 4.0),
                new Filme("Filme 6", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        assertThat(resultado.getValor(), is(14.0));
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
        boolean segunda = DataUtils.verificarDiaSemana(resultado.getDataRetorno(), Calendar.SUNDAY);
        Assert.assertTrue(segunda);
    }
}
