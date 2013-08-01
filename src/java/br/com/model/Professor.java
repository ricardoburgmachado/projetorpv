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
public class Professor {
    
    private int id;
    private String nome;
    private int siape;

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
     * @return the siape
     */
    public int getSiape() {
        return siape;
    }

    /**
     * @param siape the siape to set
     */
    public void setSiape(int siape) {
        this.siape = siape;
    }
    
}
