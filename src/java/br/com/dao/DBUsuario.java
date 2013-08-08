package br.com.dao;
    

import br.com.model.Papel;
import br.com.model.Projeto;
import java.sql.Statement;
import java.util.ArrayList;
import Exceptions.PersistenciaException;
import br.com.model.Aluno;
import br.com.model.Campus;
import br.com.model.Coordenador;
import br.com.model.Permissao;
import br.com.model.ProReitor;
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
    private ConnectionFactory connectionFactory;

    public DBUsuario(ConnectionFactory connectionFactory) {

        this.connectionFactory = connectionFactory;
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
                    Professor a = new Professor("", "");
                    a.setLogin(resultSet.getString("login").toString());
                    a.setNome(resultSet.getString("nome").toString());                    
                    listaRetorno.add(a);
                } else if (p.toString().equalsIgnoreCase("ALUNO")) {                    
                    Aluno a = new Aluno("", Campus.ALEGRETE);
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

    @Override
    public Usuario autentica(String login, String senha) throws PersistenciaException {

        Connection con = connectionFactory.createConnectionPostgres();
        Usuario user = null;
        PreparedStatement stmt = null;
        ResultSet result;

        try {

            stmt = con.prepareStatement("select login, senha, nome, campus, papel.descricao as papel, permissao.descricao as permissao from usuario inner join usuario_papel using (login) inner join papel using(id_papel) inner join papel_permissao using (id_papel) inner join permissao using(id_perm) where login=? and senha=?");
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
                connection.close();
            } catch (SQLException sqle) {
                
                throw new PersistenciaException("Erro ao fechar conexão com o banco de dados", sqle);
            }
        } 
    }

    private Usuario createUsuario(ResultSet result) throws PersistenciaException {

        try {

            result.next();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Login e senha não conferem", sqle);
        }

        try {

            String papelUsuario = result.getString("papel");
            Usuario user = instanceUsuario(papelUsuario);
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
        }
    }

    private Usuario instanceUsuario(String papel) {

        papel = papel.toLowerCase();

        if (papel.equals("aluno")) {
            return new Aluno("", Campus.ALEGRETE);
        } else if (papel.equals("coordenador")) {
            return new Coordenador("", Campus.ALEGRETE);
        } else if (papel.equals("proreitor")) {
            return new ProReitor("", Campus.ALEGRETE);
        } else if (papel.equals("professor")) {
            return new Professor("", Campus.ALEGRETE);
        }

        return null;
    }
}
