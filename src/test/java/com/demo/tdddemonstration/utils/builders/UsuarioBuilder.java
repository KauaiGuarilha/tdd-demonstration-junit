package com.demo.tdddemonstration.utils.builders;

import com.demo.tdddemonstration.model.entity.Usuario;

public class UsuarioBuilder {

    /*
     A utilidade de se criar um Builder, é evitar com que seja feita a criação do mesmo objeto várias vezes
     e fazer com que o valor não seja alterado no decorrer dos teste, quando não há necessidade.
    */

    private Usuario usuario;

    private UsuarioBuilder (){} /* Privada a instância, para que não seja novamente instanciada. */

    public static UsuarioBuilder umUsuario(){

        UsuarioBuilder builder = new UsuarioBuilder();

        builder.usuario = new Usuario();
        builder.usuario.setNome("Usuario 1");
        return builder;
    }

    public Usuario agora(){
        return usuario;
    }
}
