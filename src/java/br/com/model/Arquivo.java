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
    
    private int idArquivo;
    private String nomeArquivo;
    private String extensao;
    private byte[] dados;

    public Arquivo(String nomeArquivo, String extensao, byte[] dados) {
        this.nomeArquivo = nomeArquivo;
        this.extensao = extensao;
        this.dados = dados;
    }

    public Arquivo(int idArquivo, String nomeArquivo, String extensao, byte[] dados) {
        this.idArquivo = idArquivo;
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

    public int getIdArquivo() {
        return idArquivo;
    }
}
