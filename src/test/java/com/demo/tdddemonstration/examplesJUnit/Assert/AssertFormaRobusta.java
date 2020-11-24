package com.demo.tdddemonstration.examplesJUnit.Assert;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import com.demo.tdddemonstration.model.service.LocacoesService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

public class AssertFormaRobusta {

    /*
     A forma elegante funciona bem, quando apenas a exceção importa
     Caso precise da mensagem, terá que utilizar a forma robusta ou a forma nova
    */

    //Forma Robusta : Valida se o usuário está null
    @Test
    public void testesFormaRobustaValidaUsuarioVazio() throws FilmeSemEstoqueException {

        //Cenário
        LocacoesService services = new LocacoesService();
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
        Usuario usuario = new Usuario("Usuario 1");

        //Ação
        try {
            services.alugarFilmes(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuário vazio"));
            e.printStackTrace();
        }

        System.out.println("Forma Robusta");
    }

    @Rule
    public ExpectedException exception  = ExpectedException.none();

    //Forma nova : Valida se o usuário está null
    @Test
    public void testesFormaRobustaValidaFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {

        //Cenário
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");

        //É esperado que um Exception seja lançado
        //OBS.: Na forma nova, as exception precisam ser declaradas antes da ação, como se fizesse parte do cenário
        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        //Ação
        service.alugarFilme(usuario, null);

        System.out.println("Forma nova");
    }
}
