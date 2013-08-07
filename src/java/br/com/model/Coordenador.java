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
public class Coordenador extends Usuario {

    public Coordenador(String login, String senha, String nome, List<Permissao> permissoes) {
        super(login, senha, nome, permissoes);
    }

    public Coordenador(String login, String senha, String nome) {
        super(login, senha, nome);
    }

    public Coordenador(String login, String senha) {
        super(login, senha);
    }

    public Coordenador(String nome) {
        super(nome);
    }
    
    
}
