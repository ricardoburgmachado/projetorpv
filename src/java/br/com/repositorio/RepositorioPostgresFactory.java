/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import br.com.dao.ConnectionFactory;
import br.com.dao.DBEdital;
import br.com.dao.DBProjeto;
import br.com.dao.DBUsuario;

/**
 *
 * @author rafael
 */
public class RepositorioPostgresFactory implements AbstractRepositorioFactory{

    @Override
    public RepositorioUsuario createRepositorioUsuario() {
        
        return new RepositorioUsuario(new DBUsuario(ConnectionFactory.getInstance()));
    }

    @Override
    public RepositorioProjeto createRepositorioProjeto() {
        return new RepositorioProjeto(new DBProjeto(ConnectionFactory.getInstance().createConnectionPostgres()));
    }

    @Override
    public RepositorioEdital createRepositorioEdital() {
        
        return new RepositorioEdital(new DBEdital(ConnectionFactory.getInstance()));
    }
    
    
    
}
