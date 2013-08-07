/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author Ricardo
 */
public abstract class Usuario {
    
    private String login;
    private String senha;
    private String nome;
    private Permissao tipoAut;

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the tipoAut
     */
    public Permissao getTipoAut() {
        return tipoAut;
    }

    /**
     * @param tipoAut the tipoAut to set
     */
    public void setTipoAut(Permissao tipoAut) {
        this.tipoAut = tipoAut;
    }
           
}
