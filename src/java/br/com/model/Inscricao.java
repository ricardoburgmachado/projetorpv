/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author rafael
 */
public class Inscricao {
    
    private Projeto projeto;
    private Edital edital;
    private Arquivo arquivo;

    public Inscricao(Projeto projeto, Edital edital, Arquivo arquivo) {
        this(projeto, edital);
        this.arquivo = arquivo;
    }

    public Inscricao(Projeto projeto, Edital edital) {
        this.projeto = projeto;
        this.edital = edital;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public Edital getEdital() {
        return edital;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }
    
    
    
}
