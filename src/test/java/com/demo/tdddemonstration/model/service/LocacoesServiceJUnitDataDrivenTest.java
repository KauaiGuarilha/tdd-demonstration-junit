package com.demo.tdddemonstration.model.service;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacoesService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class LocacoesServiceJUnitDataDrivenTest {

    private LocacoesService service;

    @Parameter
    public List<Filme> filmes;

    @Parameter(value = 1)
    public Double valorLocacao;

    @Parameter(value = 2)
    public String cenario;

    private static Filme filme1 = new Filme("Filme 1", 2, 4.0);
    private static Filme filme2 = new Filme("Filme 2", 2, 4.0);
    private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
    private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
    private static Filme filme5 = new Filme("Filme 5", 2, 4.0);
    private static Filme filme6 = new Filme("Filme 6", 2, 4.0);

    @Before
    public void setup(){
        service = new LocacoesService();
    }

    /*
     Data-driven testing: Teste orientado a dados.
    */

    @Parameters(name = "{2}")
    public static Collection<Object[]> getParametros(){
        return Arrays.asList(new Object[][]{
            {Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes: 25%"},
            {Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 Filmes: 50%"},
            {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes: 75%"},
            {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 Filmes: 100%"},
        });
    }

    @Test
    public void testeLocacao() throws FilmeSemEstoqueException, LocadoraException{

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(valorLocacao));
    }

    @Test
    public void print(){
        System.out.println(valorLocacao);
    }
}
