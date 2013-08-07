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
public class PersistenciaFactoryPostgres extends AbstractPersistenciaFactory {

    @Override
    public RepositorioProjeto createPersistenciaProjeto() {

        return new RepositorioProjeto(new DBProjeto(ConnectionFactory.createConnectionPostgres()));
    }

    @Override
    public RepositorioUsuario createPersistenciaUsuario() {

        return new RepositorioUsuario(new DBUsuario(ConnectionFactory.createConnectionPostgres()));
    }
    
    
}
