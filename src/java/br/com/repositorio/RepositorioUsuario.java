/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.PersistenciaException;
import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;

/**
 *
 * @author rafael
 */
public class RepositorioUsuario {
    
    private UsuarioDAO usuarioDAO;

    public RepositorioUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    public Usuario autenticaUsuario(String login, String senha) throws PersistenciaException{
        
        return null;
    }
}
