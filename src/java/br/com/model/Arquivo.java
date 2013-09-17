/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author rafael
 */
public class Arquivo {
    
    private String nomeArquivo;
    private String extensao;
    private byte[] dados;

    public Arquivo(String nomeArquivo, String extensao, byte[] dados) {
        this.nomeArquivo = nomeArquivo;
        this.extensao = extensao;
        this.dados = dados;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getExtensao() {
        return extensao;
    }

    public byte[] getDados() {
        return dados;
    }
    
    
    
}
