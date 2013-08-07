/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.dao;

import br.com.model.Projeto;

/**
 *
 * @author rafael
 */
public abstract class AbstractPersistenciaFactory {
    
    public abstract RepositorioProjeto createPersistenciaProjeto();
    
    public abstract RepositorioUsuario createPersistenciaUsuario();
   
    
   
    
}
