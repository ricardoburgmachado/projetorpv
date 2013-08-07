/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Aluno;
import br.com.model.AreaConhecimento;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.TipoProjeto;
import br.com.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class DBProjeto implements ProjetoDAO {

    private Connection connection;
    private Statement statement = null;
    private static final String PROJETO = "projeto";
    private static final String AREA_CONHECIMENTO = "area_conhecimento";
    private static final String TIPO_PROJETO = "tipo_projeto";

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

    @Override
    public ArrayList<AreaConhecimento> listarAreas() throws PersistenciaException {
        String query = "SELECT * FROM " + AREA_CONHECIMENTO;

        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        ArrayList<AreaConhecimento> listaRetorno = new ArrayList<AreaConhecimento>();
        try {

            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                AreaConhecimento a = new AreaConhecimento();                
                a.setId(resultSet.getInt("id_area"));
                a.setArea(resultSet.getString("area").toString());
                listaRetorno.add(a);
            }

        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível listar as áreas de conhecimento cadastradas no banco", ex);
        }
        return listaRetorno;
    }

    @Override
    public ArrayList<TipoProjeto> listarTiposProjeto() throws PersistenciaException {

        String query = "SELECT * FROM " + TIPO_PROJETO;

        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        ArrayList<AreaConhecimento> listaRetorno = new ArrayList<AreaConhecimento>();
        try {

            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {                          
                listaRetorno.add(TipoProjeto.fromString(""));
            }

        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível listar as áreas de conhecimento cadastradas no banco", ex);
        }
        return listaRetorno;
    }

}
