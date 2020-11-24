package com.demo.tdddemonstration.examplesJUnit.Assert;

import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacoesService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AssertTest1 {

    @Test
    public void testAssert(){

        Assert.assertTrue(true);
        Assert.assertFalse(false);

        // assertEquals não funciona muito bem quando se trata de números flutuantes, como Float ou Double
        Assert.assertEquals(1, 1);

        // assertNotEquals para verificar se não são iguais
        Assert.assertNotEquals(1,2);

        /*
         Também é possível por um comentário no Assert
         Assert.assertEquals("Erro na compraração entre Dois Inteiros", 1, 2);
        */

        /*
         Quando um assertEquals recebe um valor Flutuante, ele exige que coloque um delta, no caso (1.1, 1.1, 0,01); mais um parâmetro
         O Delta é o valor da margem de erro entre os dois valores recebidos
        */
        Assert.assertEquals(1.1, 1.1, 0.01); //
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        // Para comparar Tipos primitivos, existe duas formas e são elas :
        Integer i = 2; // Integer.valueOf() ou intValue()
        int i2 = 2;  // Integer.valueOf() ou i2

        Assert.assertEquals(Integer.valueOf(i), Integer.valueOf(i2));
        //Ou
        Assert.assertEquals(i.intValue(), i2);

        /*
         String também podem ser comparadas no Assert.assertEquals
         Porém, da forma abaixo, ele vai comparar até se a palavra está em caixa alta ou caixa baixa, no caso só passa o mesmo Radical
        */
        Assert.assertEquals("Esperado", "Esperado");

        // Para ignorar a caixa alta, terá que ser Booleano com assertTrue e com IgnoreCase :
        Assert.assertTrue("Esperado".equalsIgnoreCase("Esperado"));

        // Para validar os Radicais, terá que usar Booleano com assertTrue
        Assert.assertTrue("Esperado".startsWith("Es"));

        /*
         Para que a comparação de dois objetos complete, precisará ter em cada classe, a implementação do Equals
         Basta ir na classe e gerar
        */
        Usuario usuario = new Usuario("Usuario");
        Usuario usuario2 = new Usuario("Usuario");

        Assert.assertEquals(usuario, usuario2);

        // Comparação em nível de Instância, para garantir que os dois Objectos são da mesma Instância
        Assert.assertSame(usuario, usuario);

        // Ou assim
        Usuario usuario3 = usuario;
        Assert.assertSame(usuario, usuario3);

        //Verificar se Dois Objetos são Diferentes
        Assert.assertNotSame(usuario, usuario2);

        //Verificar se o objeto está nulo
        Usuario usuario4 = null;
        Assert.assertNull(usuario4);
        //Ou
        Assert.assertTrue(usuario4 == null);

        //Verificar se o objeto não estão NULL
        Assert.assertNotNull(usuario);
    }

    @Test
    public void testAssertThat(){

        //Cenário
        LocacoesService services = new LocacoesService();
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));

        //Ação
        Locacao locacao = null;
        try {
            locacao = services.alugarFilmes(usuario, filmes);

            /*
             Neste caso, as ordens invertem. Agora será Atual e esperado
             O valor esperado, será representado pelo matcher
            */
            Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0));//O JUnit já tem alguns MATCHERs implementados, basta chamá-lo

            //Outra forma de fazer, é com o equalTo. Verifique que o valor da locação é Igual a 5.0
            Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
            Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0)));
            Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
            Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
