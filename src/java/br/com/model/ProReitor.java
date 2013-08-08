package br.com.model;

import java.util.List;

/**
 *
 * @author rafael
 */
public class ProReitor extends Usuario {

    public ProReitor(String login, String senha, String nome, Campus campus, List<Permissao> permissoes) {
        super(login, senha, nome, campus, permissoes);
    }

    public ProReitor(String login, String senha, String nome, Campus campus) {
        super(login, senha, nome, campus);
    }

    public ProReitor(String login, String senha) {
        super(login, senha);
    }

    public ProReitor(String nome, Campus campus) {
        super(nome, campus);
    }

}
