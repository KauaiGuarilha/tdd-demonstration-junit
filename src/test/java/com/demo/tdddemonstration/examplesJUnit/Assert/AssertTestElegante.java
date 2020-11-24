package com.demo.tdddemonstration.examplesJUnit.Assert;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacoesService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AssertTestElegante {

    /*
     A forma elegante funciona bem, quando apenas a exceção importa.
     Caso precise da mensagem, terá que usar a forma robusta ou a forma nova
    */

    @Test
    public void testesComAssertsElegante1() {

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        try {
            services.alugarFilmes(usuario, filmes);
            Assert.fail("Deveria me lançar uma exceção");
        }catch (Exception e){
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem Estoque"));
        }
    }

    //Versão "Elegante", Irá mostrar a exceção, porque é exatamente isso que ele espera
    @Test(expected = Exception.class)
    public void testesElegantes2() throws Exception {

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

        //Ação
        services.alugarFilmes(usuario, filmes);
    }

    //Versão "Elegante", agora com uma exception específica do problema
    @Test(expected = FilmeSemEstoqueException.class)
    public void testesElegantes3() throws Exception {

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

        //Ação
        services.alugarFilmes(usuario, filmes);
    }
}
