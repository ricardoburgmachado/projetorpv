/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Usuario;
import org.hibernate.Session;

/**
 *
 * @author rafael
 */
public class UsuarioHibernate implements UsuarioDAO {
    
    private Session session;

    public UsuarioHibernate() {
    }

    @Override
    public Usuario autentica(String login, String senha) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
