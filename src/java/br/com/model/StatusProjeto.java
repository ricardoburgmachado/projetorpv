/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author rafael
 */
public enum StatusProjeto {
    
    CRIADO("Não homologado"), SUBMETIDO_HOMOLOGACAO("Submetido para homologação"), HOMOLOGADO("Homologado");
    
    private String descricao;
    
    StatusProjeto(String d){
        this.descricao = d;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
}
