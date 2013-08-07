/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ConnectionFactory {

    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    
    private ConnectionFactory(){}
    
    public static ConnectionFactory getInstance(){
        
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
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gipa_database", "postgres", "postgres");
        } catch (SQLException ex) {
            System.out.println("Erro ao obter conexão " + ex);
        }

        return con;
    }
}
