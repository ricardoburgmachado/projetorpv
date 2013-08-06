/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author rafael
 */
public class ConnectionFactory {
    
    public static Connection createConnectionPostgres(){
        
        //Properties p = new PropertiesFactory().getPropertiesBDPostgres();
        
        Connection con = null;
        /*
        try {
            Class.forName(p.getProperty("driverPostgres"));
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao obter driver de conexão " + ex);
        }
        try {
            con = DriverManager.getConnection(p.getProperty("urlPostgres"), p.getProperty("user"), p.getProperty("password"));
        } catch (SQLException ex) {
            System.out.println("Erro ao obter conexão " + ex);
        }
                    
        return con;
        */
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
}
