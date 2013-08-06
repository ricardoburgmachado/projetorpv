/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Projeto;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Ricardo
 */
public class DBProjeto implements ProjetoDAO{

    private Connection connection;
    private Statement statement = null;
    private static final String PROJETO = "projeto";    

    public DBProjeto(Connection connection) {

        this.connection = connection;
    }
    
    
    
    
    @Override
    public void inserir(Projeto p) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void obter(Projeto p) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Projeto p) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Projeto p) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
