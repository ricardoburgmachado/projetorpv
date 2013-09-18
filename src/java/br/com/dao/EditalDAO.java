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
    
    boolean exists(int idEdital) throws PersistenciaException;
    
    boolean exclui(int idEdital, int idResponsavel) throws PersistenciaException;
    
    int adicionaArquivo(Arquivo arquivo) throws PersistenciaException;
    
    void inscreveProjetoEdital(int idProjeto, int idEdital) throws PersistenciaException;
    
    void inscreveProjetoEdital(Inscricao inscricao) throws PersistenciaException;
    
    public List<Edital> listarEditais(int idResponsavel) throws PersistenciaException;
}
