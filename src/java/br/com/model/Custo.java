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
public class Custo {
    
    private int id;
    private int idProjeto;
    private TipoCusto tipoCusto;
    private String descricao;
    private double valor;

    public Custo(){}
    
    /*
    public Custo(int id, int idProj, TipoCusto tipo, String desc, double val){    
        setId(id);
        setIdProjeto(idProjeto);
        setTipoCusto(tipoCusto);
        setDescricao(desc);
        setValor(val);
    }
    */
    
    public Custo(int idProj,TipoCusto tipo, String desc, double val){    
        this.idProjeto = idProj;
        this.tipoCusto = tipo;        
        setDescricao(desc);
        setValor(val);
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
     * @return the tipoCusto
     */
    public TipoCusto getTipoCusto() {
        return tipoCusto;
    }

    //public String getTipoCustoString() {
    //    return tipoCusto.toString();
    //}
    
    /**
     * @param tipoCusto the tipoCusto to set
     */
    public void setTipoCusto(TipoCusto tipoCusto) {
        this.tipoCusto = tipoCusto;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the idProjeto
     */
    public int getIdProjeto() {
        return idProjeto;
    }

    /**
     * @param idProjeto the idProjeto to set
     */
    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
    
}
