/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rafael
 */
public abstract class Usuario {

    private String login;
    private String senha;
    private String nome;
    private List<Permissao> permissoes;

    public Usuario(String login, String senha, String nome, List<Permissao> permissoes) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.permissoes = permissoes;
    }

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        permissoes = new LinkedList<>();
    }

    public Usuario(String login, String senha) {
        this(login, senha, null);
    }

    public Usuario(String nome) {
        this(null, null, nome);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public void addPermissao(Permissao permissao) {

        this.permissoes.add(permissao);
    }

    public void removePermissao(Permissao permissao) {

        this.permissoes.remove(permissao);
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", senha=" + senha + ", nome=" + nome + ", permissoes=" + permissoes + '}';
    }
}
