/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Papel;
import br.com.model.Permissao;
import br.com.model.Projeto;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface UsuarioDAO {

    public ArrayList listar(Papel p) throws PersistenciaException;

    public void obter(Projeto p) throws PersistenciaException;

}
