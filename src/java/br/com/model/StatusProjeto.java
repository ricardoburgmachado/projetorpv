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
    
    CRIADO("Não homologado"), SUBMETIDO_HOMOLOGACAO ("asdfg"), HOMOLOGADO ("kjhgfd");
    
    private String string;
    
    private StatusProjeto(String string){
        
        this.string = string;
    }
    
    public String getString(){
        
        return this.string;
    }
}
