/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class Edital {

    private int id;
    private String titulo;
    private TipoProjeto tipo;
    private Date prazoInicial;
    private Date prazoFinal;
    private boolean documento;//TRUE caso exista um arquivo 
    private ProReitor proReitor;
    private Arquivo arquivo;

    public Edital() {
    }

    public Edital(int id, String titulo, TipoProjeto tipo, Date prazoIni, Date prazoFim, boolean docum, ProReitor proReitor) {
        
        this(titulo, tipo, prazoIni, prazoFim, docum, proReitor);
        this.id = id;
    }

    public Edital(String titulo, TipoProjeto tipo, Date prazoIni, Date prazoFim, boolean docum, ProReitor proReitor) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.prazoInicial = prazoIni;
        this.prazoFinal = prazoFim;
        this.documento = docum;
        this.proReitor = proReitor;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the prazoInicial
     */
    public Date getPrazoInicial() {
        return prazoInicial;
    }

    /**
     * @param prazoInicial the prazoInicial to set
     */
    public void setPrazoInicial(Date prazoInicial) {
        this.prazoInicial = prazoInicial;
    }

    /**
     * @return the prazoFinal
     */
    public Date getPrazoFinal() {
        return prazoFinal;
    }

    /**
     * @param prazoFinal the prazoFinal to set
     */
    public void setPrazoFinal(Date prazoFinal) {
        this.prazoFinal = prazoFinal;
    }

    /**
     * @return the documento
     */
    public boolean isDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(boolean documento) {
        this.documento = documento;
    }

    /**
     * @return the proReitor
     */
    public ProReitor getProReitor() {
        return proReitor;
    }

    /**
     * @param proReitor the proReitor to set
     */
    public void setProReitor(ProReitor proReitor) {
        this.proReitor = proReitor;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public TipoProjeto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProjeto tipo) {
        this.tipo = tipo;
    }

}
