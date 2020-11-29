package com.demo.tdddemonstration.model.service.junit;

import com.demo.tdddemonstration.builders.FilmeBuilder;
import com.demo.tdddemonstration.builders.UsuarioBuilder;
import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.matchers.DiaSemanaMatcher;
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

import static com.demo.tdddemonstration.matchers.MatchersProprios.*;

public class LocacoesServiceJUnitBuilderTest {

    private LocacoesService service;

    @Before
    public void setup(){
        service = new LocacoesService();
    }

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Test
    public void deveriaVerificarDiferencaDeDias() throws FilmeSemEstoqueException, LocadoraException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //Cenário
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0));

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        error.checkThat(resultado.getDataRetorno(), hojeComDiferencaDias(2));
    }

    @Test
    public void deveriaVerificarDiferencaDeDias2() throws FilmeSemEstoqueException, LocadoraException {

        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //Cenário
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());

        //Ação
        Locacao resultado = service.alugarFilmes(usuario, filmes);

        //Verificação
        error.checkThat(resultado.getDataRetorno(), hojeComDiferencaDias(2));
    }
}
