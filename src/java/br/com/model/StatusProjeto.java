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
    
    CRIADO("Não homologado"), SUBMETIDO_HOMOLOGACAO("Submetido para homologação"), HOMOLOGADO("Homologado"), INSCRITO("Inscrito");
    
    private String descricao;
    
    StatusProjeto(String d){
        this.descricao = d;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public static StatusProjeto fromString(String text) {
        if (text != null) {
            for (StatusProjeto c : StatusProjeto.values()) {
                if (text.equalsIgnoreCase(c.toString())) {
                    return c;
                }
            }
        }
        return null;
    }
}
