package br.com.dao;

import br.com.model.Projeto;
import java.sql.Statement;
import java.util.ArrayList;
import Exceptions.PersistenciaException;
import br.com.model.Aluno;
import br.com.model.Campus;
import br.com.model.Externo;
import br.com.model.Permissao;
import br.com.model.Professor;
import br.com.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private PostgresConnectionFactory connectionFactory;

    public DBUsuario(){}
    
    public DBUsuario(PostgresConnectionFactory connectionFactory) {

        this.connectionFactory = connectionFactory;
    }

    

    @Override
    public ArrayList<Usuario> listar(String papel) throws PersistenciaException {

        String query = "SELECT * FROM usuario NATURAL JOIN usuario_papel NATURAL JOIN papel where papel.descricao = '"+papel.toString()+"'";
        
        System.out.println("******************** QUERY -> "+query);
        
        this.connection = connectionFactory.createConnection();
        
        PreparedStatement stmt;
        ResultSet resultSet;
        ArrayList<Usuario> listaRetorno = new ArrayList<Usuario>();
        try {

            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                

                if (papel.toString().equalsIgnoreCase("PROFESSOR")) {                    
                    Professor a = new Professor();
                    a.setId(resultSet.getInt("id_usuario"));
                    a.setNome(resultSet.getString("nome").toString());                    
                    listaRetorno.add(a);
                } else if (papel.toString().equalsIgnoreCase("ALUNO")) {                    
                    Aluno a = new Aluno();
                    a.setId(resultSet.getInt("id_usuario"));
                    a.setNome(resultSet.getString("nome").toString());                    
                    listaRetorno.add(a);
                }else if (papel.toString().equalsIgnoreCase("EXTERNO")) {                    
                    Externo a = new Externo();
                    a.setId(resultSet.getInt("id_usuario"));
                    a.setNome(resultSet.getString("nome").toString());                    
                    listaRetorno.add(a);
                }
            }

        } catch (Exception ex) {
            System.err.println("ERRO: "+ex.getMessage()+ " -> causa:  "+ex.getCause());
            throw new PersistenciaException("Não foi possível listar os usuários cadastrados no banco", ex);
        }
        return listaRetorno;
    }

    @Override
    public void obter(Projeto p) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Usuario autentica(String login, String senha) throws PersistenciaException {

        Connection con = connectionFactory.createConnection();
        Usuario user = null;
        PreparedStatement stmt = null;
        ResultSet result;

        try {

            stmt = con.prepareStatement("select id_usuario, login, senha, nome, campus, papel.descricao as papel, permissao.descricao as permissao from usuario inner join usuario_papel using (id_usuario) inner join papel using(id_papel) inner join papel_permissao using (id_papel) inner join permissao using(id_perm) where login=? and senha=?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Não foi possível acessar o banco de dados para autenticação", sqle);
        }

        try {

            result = stmt.executeQuery();
            return createUsuario(result);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Erro ao buscar por usuário no banco de dados", sqle);
        }finally{
            
            try {
                con.close();
            } catch (SQLException sqle) {
                
                throw new PersistenciaException("Erro ao fechar conexão com o banco de dados", sqle);
            }
        } 
    }

    private Usuario createUsuario(ResultSet result) throws PersistenciaException {

        try {
            if (!result.next()) {

                throw new PersistenciaException("Login e senha não conferem!");
            }
        } catch (SQLException ex) {
            
            throw new PersistenciaException("Erro ao acessar o resultado da consulta", ex);
        }

        try {

            String papelUsuario = result.getString("papel");
            Usuario user = Usuario.instantiateUsuario(papelUsuario);
            user.setId(result.getInt("id_usuario"));
            user.setLogin(result.getString("login"));
            user.setNome(result.getString("nome"));
            user.setSenha(result.getString("senha"));
            user.setCampus(Campus.valueOf(result.getString("campus")));

            do {

                user.addPermissao(Permissao.valueOf(result.getString("permissao")));
            } while (result.next());

            return user;
        } catch (SQLException sqle) {

            throw new PersistenciaException("Não foi possível atribuir as propriedades ao usuário", sqle);
        } finally {

            try {
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
