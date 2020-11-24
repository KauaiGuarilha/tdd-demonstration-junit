package com.demo.tdddemonstration.model.service.junit;

import com.demo.tdddemonstration.model.entity.Filme;
import com.demo.tdddemonstration.model.entity.Locacao;
import com.demo.tdddemonstration.model.entity.Usuario;
import com.demo.tdddemonstration.model.service.LocacaoService;
import com.demo.tdddemonstration.utils.DataUtils;
import org.junit.jupiter.api.Test;
import java.util.Date;
import org.junit.Assert;

public class LocacaoServiceJUnityAssertTest {

    @Test
    public void testeAssert1(){

        //Cenário

        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        //Ação

        Locacao locacao = service.alugarFilme(usuario, filme);

        //Verificação

        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }
}
