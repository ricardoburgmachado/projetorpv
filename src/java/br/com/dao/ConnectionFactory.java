package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ConnectionFactory {

    public static Connection createConnectionPostgres() {

        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao obter driver de conexão " + ex);
        }

        try{
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banco_gipa", "postgres", "postgres");
        } catch (SQLException ex) {
            System.out.println("Erro ao obter conexão " + ex);
        }
                    
        return con;
    }
}
