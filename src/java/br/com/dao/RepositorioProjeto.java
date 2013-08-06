/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

/**
 *
 * @author Ricardo
 */
public class RepositorioProjeto {
    
     private ProjetoDAO projDAO;

    /**
     * Construtor
     * @param projetoDAO 
     */
    public RepositorioProjeto(ProjetoDAO pDAO) {

        this.projDAO = pDAO;
    }
}
