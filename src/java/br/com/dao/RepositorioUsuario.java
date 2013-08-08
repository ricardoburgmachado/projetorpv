/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.model.Papel;
import br.com.model.Permissao;
import br.com.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class RepositorioUsuario {

    private UsuarioDAO usuDao;

    /**
     * Construtor
     *
     * @param UsuarioDAO
     */
    public RepositorioUsuario(UsuarioDAO uDAO) {
        this.usuDao = uDAO;
    }

    public ArrayList<Usuario> listar(Papel p){  
        return usuDao.listar(p);
    }
    
    
    
    
}
