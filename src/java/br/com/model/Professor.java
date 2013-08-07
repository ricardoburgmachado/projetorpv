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
public class Professor extends Usuario{

    public Professor(String login, String senha, String nome, List<Permissao> permissoes) {
        super(login, senha, nome, permissoes);
    }

    public Professor(String login, String senha, String nome) {
        super(login, senha, nome);
    }

    public Professor(String login, String senha) {
        super(login, senha);
    }

    public Professor(String nome) {
        super(nome);
    }
 
    
}
