/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Aluno;
import br.com.model.AreaConhecimento;
import br.com.model.Custo;
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
    private static final String CUSTO = "custo";

    public DBProjeto(Connection connection) {

        this.connection = connection;
    }

    @Override
    public int inserir(Projeto p) throws PersistenciaException {

        String query = "INSERT INTO " + PROJETO + " (titulo, palavras_chave, resumo, sigilo, id_area, tipo_proj) VALUES (?,?,?,?,?,?) ";

        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getPalavrasChave());
            stmt.setString(3, p.getResumo());
            stmt.setBoolean(4, p.getSigilo());
            stmt.setInt(5, p.getAreaConhecimento().getId());
            stmt.setString(6, p.getTipoProjeto().toString());
            int retorno = stmt.executeUpdate();

            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: o projeto não foi cadastrado no banco de dados");
                return -1;
            } else {
                System.out.println("RETORNO TRUE -> BD: o projeto foi cadastrado no banco de dados");
                
                //inserirCusteios(p.getCusteios());                               
                
                return ultimoRegistro(PROJETO);
            }

        } catch (SQLException sqle) {

            throw new PersistenciaException("Não foi possível inserir o projeto no banco de dados!", sqle);
        }

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

    private int ultimoRegistro(String tabela) {
        String query = "SELECT MAX(id_proj) FROM " + tabela;
        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível recuperar o id do último registro cadastrado na tabela: " + tabela + " no BD", ex);
        }
    }

    @Override
    public void inserirCusteios(ArrayList<Custo> c){
        
        System.out.println("QUANTIDADE CUSTEIOS: "+ c.size());        
        for (int i = 0; i < c.size(); i++) {
        //    insereCusteio(custeios.get(i));
            System.out.println("****** "+c.get(i).getTipoCustoString()+ " | "+c.get(i).getDescricao()+ " | "+c.get(i).getValor()+ " | "+c.get(i).getIdProjeto()+ " ");
        }
    }

    public void insereCusteio(Custo custo) {
        
        String query = "INSERT INTO " + CUSTO + " (tipo, valor, descricao, id_proj) VALUES (?,?,?,?) ";
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, custo.getTipoCusto().toString());
            stmt.setDouble(2, custo.getValor());
            stmt.setString(3, custo.getDescricao());
            stmt.setInt(4, custo.getIdProjeto());

            int retorno = stmt.executeUpdate();

            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: o custeio não foi cadastrado no banco de dados");                
            } else {
                System.out.println("RETORNO TRUE -> BD: o custeio foi cadastrado no banco de dados");
            }

        } catch (SQLException sqle) {

            throw new PersistenciaException("Não foi possível cadastrar o custeio no banco de dados!", sqle);
        }
    }

    @Override
    public void inserirCapitais(ArrayList<Custo> c) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
