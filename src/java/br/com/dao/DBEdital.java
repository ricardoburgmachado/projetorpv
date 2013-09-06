/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Arquivo;
import br.com.model.Edital;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author rafael
 */
public class DBEdital implements EditalDAO {

    private ConnectionFactory factory;

    public DBEdital(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void adiciona(Edital edital) throws PersistenciaException {
        
        String sql = "insert into edital (prazo_final, prazo_inicial, titulo, id_usuario, tipo_edital, id_arquivo) values (?,?,?,?,?,?)";
        Connection con = factory.createConnectionPostgres();
        PreparedStatement stmt;
        
        try {

            stmt = con.prepareStatement(sql);
            stmt.setDate(1, new Date(edital.getPrazoFinal().getTime()));
            stmt.setDate(2, new Date(edital.getPrazoInicial().getTime()));
            stmt.setString(3, edital.getTitulo());
            stmt.setInt(4, edital.getProReitor().getId());
            stmt.setString(5, edital.getTipo().toString());
            stmt.setInt(6, adicionaArquivo(edital.getArquivo()));
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao configurar inserção do edital!", sqle);
        }
        
        try {

            stmt.execute();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao inserir edital!", sqle);
        } finally {

            factory.close(con);
        }
        
    }

    private int adicionaArquivo(Arquivo arquivo) throws PersistenciaException {

        String sql = "insert into arquivo (nome_arquivo, extensao, dados) values (?, ?, ?)";
        Connection con = factory.createConnectionPostgres();
        PreparedStatement stmt;

        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, arquivo.getNomeArquivo());
            stmt.setString(2, arquivo.getExtensao());
            stmt.setBytes(3, arquivo.getDados());
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao configurar inserção do arquivo!", sqle);
        }

        try {

            stmt.execute();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao inserir arquivo!", sqle);
        } finally {

            factory.close(con);
        }

        return getMaxIDArquivo();
    }

    private int getMaxIDArquivo() throws PersistenciaException {

        String sql = "select max(id) as id from arquivo";
        Connection con = factory.createConnectionPostgres();
        PreparedStatement stmt;

        try {

            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return rs.getInt("id");
            }
        } catch (SQLException sqle) {
            throw new PersistenciaException("Falha ao obter id máximo de arquivo!", sqle);
        }

        return 0;
    }
}
