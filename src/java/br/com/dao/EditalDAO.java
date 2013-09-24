/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Arquivo;
import br.com.model.Edital;
import br.com.model.Inscricao;
import br.com.model.Projeto;
import java.util.List;

/**
 *
 * @author rafael
 */
public interface EditalDAO {
    
    void adiciona(Edital edital) throws PersistenciaException;
    
    Edital obtem(int idEdital, int idResponsavel) throws PersistenciaException;
    
    Arquivo obtemArquivo(int idEdital) throws PersistenciaException;
    
    List<Arquivo> obtemArquivos(int idEdital) throws PersistenciaException;
    
    boolean exists(int idEdital) throws PersistenciaException;
    
    boolean exclui(int idEdital, int idResponsavel) throws PersistenciaException;
    
    int adicionaArquivo(Arquivo arquivo) throws PersistenciaException;
    
    void inscreveProjetoEdital(Inscricao inscricao) throws PersistenciaException;
    
    boolean verificaInscricao(int idEdital, int idProjeto) throws PersistenciaException;
    
    public List<Edital> listarEditais(int idResponsavel) throws PersistenciaException;
    
    void edita(Edital edital) throws PersistenciaException;
    
    void adicionaArquivo(Arquivo arquivo, int idEdital) throws PersistenciaException;
    
    void retifica(int idArquivo, int idEdital) throws PersistenciaException;
}
