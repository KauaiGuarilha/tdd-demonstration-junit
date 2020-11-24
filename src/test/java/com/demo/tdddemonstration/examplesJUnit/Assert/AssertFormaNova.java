package com.demo.tdddemonstration.examplesJUnit.Assert;

import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacoesService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

public class AssertFormaNova {

    /*
     A forma elegante funciona bem, quando apenas a exceção importa
     Caso precise da mensagem, terá que utilizar a forma robusta ou a forma nova
    */

    @Rule
    public ExpectedException exception  = ExpectedException.none();

    @Test
    public void testesComAssertsFormaNova() throws Exception {

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));

        //É esperado que um Exception seja lançado
        //OBS.: Na forma nova, as exception precisam ser declaradas antes da ação, como se fizesse parte do cenário
        exception.expect(Exception.class);
        exception.expectMessage("Filme sem Estoque");

        //Ação
        services.alugarFilmes(usuario, filmes);
    }
}
