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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idArquivo;
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
        final Arquivo other = (Arquivo) obj;
        if (this.idArquivo != other.idArquivo) {
            return false;
        }
        return true;
    }
}
