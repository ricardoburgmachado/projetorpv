/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Aluno;
import br.com.model.Papel;
import br.com.model.Permissao;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class DBUsuario implements UsuarioDAO {

    private Connection connection;
    private Statement statement = null;
    private static final String USUARIO = "usuario";

    public DBUsuario(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<Usuario> listar(Papel p) throws PersistenciaException {

        String query = "SELECT * FROM usuario NATURAL JOIN usuario_papel NATURAL JOIN papel where papel.descricao = '"+p.toString()+"'";
        
        //System.out.println("******************** QUERY -> "+query);
        
        PreparedStatement stmt;
        ResultSet resultSet;
        ArrayList<Usuario> listaRetorno = new ArrayList<Usuario>();
        try {

            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                
                if (p.toString().equalsIgnoreCase("PROFESSOR")) {                    
                    Professor a = new Professor();
                    a.setLogin(resultSet.getString("login").toString());
                    a.setNome(resultSet.getString("nome").toString());                    
                    listaRetorno.add(a);
                } else if (p.toString().equalsIgnoreCase("ALUNO")) {                    
                    Aluno a = new Aluno();
                    a.setLogin(resultSet.getString("login").toString());
                    a.setNome(resultSet.getString("nome").toString());                    
                    listaRetorno.add(a);
                }
            }

        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível listar os usuários cadastrados no banco", ex);
        }
        return listaRetorno;
    }

    @Override
    public void obter(Projeto p) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
