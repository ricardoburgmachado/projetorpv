package br.com.model;

import java.util.List;

/**
 *
 * @author rafael
 */
public class Coordenador extends Usuario {

    public Coordenador(String login, String senha, String nome, Campus campus, List<Permissao> permissoes) {
        super(login, senha, nome, campus, permissoes);
    }

    public Coordenador(String login, String senha, String nome, Campus campus) {
        super(login, senha, nome, campus);
    }

    public Coordenador(String login, String senha) {
        super(login, senha);
    }

    public Coordenador(String nome, Campus campus) {
        super(nome, campus);
    }
}
