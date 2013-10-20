/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class Recado {
    
    private String conteudo;
    private Usuario remetente;
    private Date dataEnvio;

    public Recado() {
    }

    public Recado(String conteudo, Usuario remetente, Date dataEnvio) {
        
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.dataEnvio = dataEnvio;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.remetente);
        hash = 17 * hash + Objects.hashCode(this.dataEnvio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recado other = (Recado) obj;
        if (!Objects.equals(this.remetente, other.remetente)) {
            return false;
        }
        if (!Objects.equals(this.dataEnvio, other.dataEnvio)) {
            return false;
        }
        return true;
    }
    
    
}
