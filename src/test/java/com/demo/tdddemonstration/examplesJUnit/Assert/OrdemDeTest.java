package com.demo.tdddemonstration.examplesJUnit.Assert;


import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemDeTest {
    /*
     Caso precise gerar os testes em ordem, a única forma, será usando a anotação o @FixMethodOrder
     Dentro podemos utilizar o MethodSorters.NAME_ASCENDING, Assim os testes serão inicializados em ordem alfabética
    */

    public static int contador = 0;

    //No caso I vem antes de V, logo começará por esse método
    @Test
    public void inicia(){

        contador = 1;
    }

    @Test
    public void verifica(){

        Assert.assertEquals(1, contador);
    }
}
