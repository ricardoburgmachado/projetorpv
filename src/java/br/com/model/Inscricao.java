/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<Recado> recados;

    public Inscricao(Projeto projeto, Edital edital, Arquivo arquivo, Date dataInscricao) {
        this.recados = new ArrayList<>();
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

    public List<Recado> getRecados() {
        return recados;
    }

    public void setRecados(List<Recado> recados) {
        this.recados = recados;
    }

    public Recado addRecado(String conteudo, Usuario remetente, Date dataEnvio) {

        Recado recado = new Recado(conteudo, remetente, dataEnvio);
        this.recados.add(recado);
        return recado;
    }

    public void removeRecado(Recado recado) {

        if (recado != null) {

            this.recados.remove(recado);
        }

    }

    public boolean isAprovada() {
        return aprovada;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }

}
