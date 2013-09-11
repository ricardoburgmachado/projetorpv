/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Arquivo;
import br.com.model.Edital;

/**
 *
 * @author rafael
 */
public interface EditalDAO {
    
    void adiciona(Edital edital) throws PersistenciaException;
    
    Edital obtem(int idEdital) throws PersistenciaException;
    
    Arquivo obtemArquivo(int idEdital) throws PersistenciaException;
    
    boolean exists(int idEdital) throws PersistenciaException;
}
