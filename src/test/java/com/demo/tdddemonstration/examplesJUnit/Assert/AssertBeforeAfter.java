package com.demo.tdddemonstration.examplesJUnit.Assert;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import com.demo.tdddemonstration.model.service.LocacoesService;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class AssertBeforeAfter {

    /*
     Pra podermos predefinir um serviço sem termos que repeti-lo,
     precisamos por no @before e instanciar uam variável visível no projeto
    */

    private LocacaoService service;
    private LocacoesService services;

    @Before
    public void setup(){
        services = new LocacoesService(); //Instanciando
        System.out.println("Before");
    }

    @After
    public void tearDown(){
        System.out.println("After");
    }

    //O @BeforeClass estático, faz com que inicie antes da instância de uma classe
    @BeforeClass
    public static void BeforeClasssetup(){
        System.out.println("Before Class");
    }

    //O @AfterClass estático, faz com que inicie após a instância de todas as classes
    @AfterClass
    public static void afterClassDown(){
        System.out.println("After Clss");
    }

    @Test
    public void testesComAssertsElegante1() {

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        try {
            services.alugarFilmes(usuario, filmes);
        }catch (Exception e){
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem Estoque"));
        }
    }

    @Test(expected = Exception.class)
    public void testesElegantes2() throws Exception {

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        services.alugarFilmes(usuario, filmes);
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void testesElegantes3() throws Exception {

        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        services.alugarFilmes(usuario, filmes);
    }
}
