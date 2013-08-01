/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import br.com.dao.UsuarioHibernate;

/**
 *
 * @author rafael
 */
public class RepositorioHibernateFactory implements AbstractRepositorioFactory{

    @Override
    public RepositorioUsuario createRepositorioUsuario() {
        
        return new RepositorioUsuario(new UsuarioHibernate());
    }
    
}
