/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Projeto;

/**
 *
 * @author Ricardo
 */
public interface ProjetoDAO {
    
     public void inserir(Projeto p) throws PersistenciaException;
     public void obter(Projeto p) throws PersistenciaException;
     public void editar(Projeto p) throws PersistenciaException;
     public void excluir(Projeto p) throws PersistenciaException;
     
     
}
