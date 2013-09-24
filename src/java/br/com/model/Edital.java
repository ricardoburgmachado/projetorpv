/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    private List<Inscricao> inscricoes;
    private ProReitor proReitor;
    private Arquivo arquivo;
    private List<Arquivo> retificacoes;


    public Edital() {
        
        this.inscricoes = new ArrayList<>();
        this.retificacoes = new ArrayList<>();
    }

    public Edital(int id, String titulo, TipoProjeto tipo, Date prazoIni, Date prazoFim, ProReitor proReitor) {
        
        this(titulo, tipo, prazoIni, prazoFim, proReitor);
        this.id = id;
    }

    public Edital(String titulo, TipoProjeto tipo, Date prazoIni, Date prazoFim, ProReitor proReitor) {
        
        this();
        this.titulo = titulo;
        this.tipo = tipo;
        this.prazoInicial = prazoIni;
        this.prazoFinal = prazoFim;
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
    
    private void addRetificacao(Arquivo arquivo){
        
        this.retificacoes.add(arquivo);
    }

    public List<Arquivo> getRetificacoes() {
        return retificacoes;
    }
    
    public void setRetificacoes(List<Arquivo> retificacoes) {
        this.retificacoes = retificacoes;
    }
    
    public Inscricao inscreveProjeto(Projeto projeto, Arquivo arquivo, Date dataInscricao){
        
        Inscricao inscricao = new Inscricao(projeto, this, arquivo, dataInscricao);
        this.inscricoes.add(inscricao);
        
        return inscricao;
    }
    
    public String organizaData(Date d){ 
        String formato = new SimpleDateFormat("dd/MM/YYYY").format(d);
        return formato;
    }
}
