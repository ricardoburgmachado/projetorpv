/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.util.List;

/**
 *
 * @author rafael
 */
public class Aluno extends Usuario {

    public Aluno(String login, String senha, String nome, Campus campus, List<Permissao> permissoes) {
        super(login, senha, nome, campus, permissoes);
    }

    public Aluno(String login, String senha, String nome, Campus campus) {
        super(login, senha, nome, campus);
    }

    public Aluno(String login, String senha) {
        super(login, senha);
    }

    public Aluno(String nome, Campus campus) {
        super(nome, campus);
    }

}
