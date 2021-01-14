package br.ce.waquino.builders;

import br.ce.wcaquino.entidades.Usuario;

public class UsuarioBuilder {

    // instancia privada do usuario
    private Usuario usuario;
    // um construtor PRIVADO == para que ninguem possa criar instancias do BUILDER externamente ao proprio builder
    private UsuarioBuilder() {}

    // criar um metodo publico estatico que retornara instancia de UsuarioBuilder =
    // ficou publico estatico para que POSSA acessar externamente sem necessidade de uma instancia
    public static UsuarioBuilder umUsuario() {
        // instancia do UsuarioBuilder
        UsuarioBuilder builder = new UsuarioBuilder();
        // inicializaqr a instancia do usuario
        builder.usuario = new Usuario();
        // setar o valor para usuario
        builder.usuario.setNome("Usuario 1");
        // retornar instancia desse UsuarioBuilder que fizemos
        return builder; // chaining method

    }

    // metodo que retornara o proprio usuario
    public Usuario agora() {
        // retorna essa instancia
        return usuario;
    }
}
