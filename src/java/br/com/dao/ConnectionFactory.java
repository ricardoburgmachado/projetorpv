package br.com.dao;

import Exceptions.PersistenciaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ConnectionFactory {

    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    private ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() {

        return connectionFactory;
    }

    public Connection createConnectionPostgres() {

        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao obter driver de conexão " + ex);
        }

        try {

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banco_gipa", "postgres", "postgres");
        } catch (SQLException ex) {
            System.out.println("Erro ao obter conexão " + ex);
        }

        return con;
    }

    public boolean close(Connection con) throws PersistenciaException {

        if(con == null){
            
            return false;
        }
        
        try {

            con.close();
        } catch (SQLException ex) {
            
            throw new PersistenciaException("Falha ao fechar conexão!", ex);
        }
        
        return true;
    }
}
