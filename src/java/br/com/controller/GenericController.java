/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Permissao;
import br.com.model.Usuario;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rafael
 */
public abstract class GenericController {
    
    public boolean verificaAutorizacao(Usuario user, Permissao permissao){
        
        return user != null && user.getPermissoes().contains(permissao);
    }
}
