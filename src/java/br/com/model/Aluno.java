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

    public Aluno(String login, String senha, String nome, List<Permissao> permissoes) {
        super(login, senha, nome, permissoes);
    }

    public Aluno(String login, String senha, String nome) {
        super(login, senha, nome);
    }

    public Aluno(String login, String senha) {
        super(login, senha);
    }

    public Aluno(String nome) {
        super(nome);
    }
    
    
}
