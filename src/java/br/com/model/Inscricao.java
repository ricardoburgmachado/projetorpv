/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.util.Date;

/**
 *
 * @author rafael
 */
public class Inscricao {

    private Projeto projeto;
    private Edital edital;
    private Arquivo arquivo;
    private Date dataInscricao;
    private boolean aprovada;
    

    public Inscricao(Projeto projeto, Edital edital, Arquivo arquivo, Date dataInscricao) {
        this.projeto = projeto;
        this.edital = edital;
        this.arquivo = arquivo;
        this.dataInscricao = dataInscricao == null ? new Date() : dataInscricao;
    }

    public Inscricao(Projeto projeto, Edital edital, Arquivo arquivo) {
        this(projeto, edital, arquivo, null);
    }

    public Inscricao(Projeto projeto, Edital edital) {
        this(projeto, edital, null);
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

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    

    public boolean isAprovada() {
        return aprovada;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }

}
