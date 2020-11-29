package com.demo.tdddemonstration.model.service;

import com.demo.tdddemonstration.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstration.exceptions.LocadoraException;
import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocacaoServiceJUnitOrdemExecucaoTest {

    /*
     A ordem de execução deles é a ordem alfabética, através da annotation @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    */

    public static int contador = 0;

    @Test
    public void inicia(){
        contador = 1;
    }

    @Test
    public void verifica(){
        Assert.assertEquals(1, contador);
    }
}
