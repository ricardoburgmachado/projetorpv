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
import br.com.model.Externo;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.TipoCusto;
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
    private static final String PARTICIPANTE = "participante";

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
    public Projeto obter(Projeto p) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Projeto obter(int id) throws PersistenciaException {

        System.out.println("****************** ENTROU NO MÉTODO OBTER");

        String query = "SELECT * FROM " + PROJETO + " WHERE id_proj = " + id;
        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            resultSet.next();
            Projeto p = new Projeto();
            p.setId(resultSet.getInt("id_proj"));
            p.setTitulo(resultSet.getString("titulo"));
            p.setResumo(resultSet.getString("resumo"));
            p.setPalavrasChave(resultSet.getString("palavras_chave"));
            p.setAreaConhecimento(getArea(id));
            p.setTipoProjeto(TipoProjeto.fromString(resultSet.getString("tipo_proj")));
            p.setSigilo(resultSet.getBoolean("sigilo"));
            p.setArquivo(resultSet.getBoolean("is_arquivo"));
            return p;

        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível obter o projeto com o ID: " + id + " no BD", ex);
        }
    }

    @Override
    public ArrayList<Aluno> getParticAlunos(int id) throws PersistenciaException {

        /*        
         FROM usuario  
         INNER JOIN usuario_papel ON usuario.id_usuario = usuario_papel.id_usuario
         INNER JOIN papel ON papel.id_papel = usuario_papel.id_papel
         INNER JOIN participante ON participante.id_usuario = usuario.id_usuario  AND participante.id_proj = 26
         */
        /*
         SELECT usuario.id_usuario 
         FROM usuario  
         INNER JOIN usuario_papel ON usuario.id_usuario = usuario_papel.id_usuario
         INNER JOIN papel ON papel.id_papel = usuario_papel.id_papel AND papel.descricao = 'ALUNO'
         INNER JOIN participante ON participante.id_usuario = usuario.id_usuario  AND participante.id_proj = 26        
         */
        String query = "SELECT usuario.id_usuario FROM usuario "
                + " INNER JOIN usuario_papel ON usuario.id_usuario = usuario_papel.id_usuario "
                + " INNER JOIN papel ON papel.id_papel = usuario_papel.id_papel AND papel.descricao = 'ALUNO' "
                + " INNER JOIN participante ON participante.id_usuario = usuario.id_usuario  AND participante.id_proj = " + id;

        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        ArrayList<Aluno> listaRetorno = new ArrayList<Aluno>();
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Aluno a = new Aluno();
                a.setId(resultSet.getInt("id_usuario"));
                listaRetorno.add(a);
            }
            return listaRetorno;
        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível listar os alunos (participantes) do projeto id: " + id, ex);
        }
    }

    @Override
    public void editar(Projeto p) throws PersistenciaException {
        String query = "UPDATE " + PROJETO + " SET titulo=?, palavras_chave=?, resumo=?, sigilo=?, id_area=?, tipo_proj=? WHERE id_proj = ?";

        System.out.println("******************** QUERY -> " + query);

        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getPalavrasChave());
            stmt.setString(3, p.getResumo());
            stmt.setBoolean(4, p.getSigilo());
            stmt.setInt(5, p.getAreaConhecimento().getId());
            stmt.setString(6, p.getTipoProjeto().toString());
            stmt.setInt(7, p.getId());

            System.out.println("******************** QUERY -> " + query);
            int retorno = stmt.executeUpdate();

            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: o projeto não foi atualizado no banco de dados");

            } else {
                System.out.println("RETORNO TRUE -> BD: o projeto foi atualizado no banco de dados");

                //inserirCusteios(p.getCusteios());                               
            }

        } catch (SQLException sqle) {

            throw new PersistenciaException("Não foi possível atualizar o projeto no banco de dados!, erro: ", sqle);
        }

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

    private void insereCusto(Custo custo) {

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
    public void inserirCustos(ArrayList<Custo> c) throws PersistenciaException {

        for (int i = 0; i < c.size(); i++) {
            insereCusto(c.get(i));
            //System.out.println("******CUSTO -> idProj: "+c.get(i).getIdProjeto()+" | tipoCusto: "+c.get(i).getTipoCusto().toString()+ " | desc:"+c.get(i).getDescricao()+ " | val: "+c.get(i).getValor()+ " | "+c.get(i).getIdProjeto()+ " ");
        }
    }

    /**
     * Método utilizado no cadastro de projetos
     * @param idProj
     * @param idPart
     * @throws PersistenciaException 
     */
    @Override
    public void inserirParticipantes(int idProj, String[] idPart) throws PersistenciaException {
        for (int i = 0; i < idPart.length; i++) {           
            inserirParticipante(idProj, Integer.parseInt(idPart[i].toString()));
        }
    }

    /**
     * Método utilizado na ediçao de projetos
     * @param idProj
     * @param list
     * @throws PersistenciaException 
     */
    @Override
    public void inserirParticipantes(int idProj, ArrayList<String> list) throws PersistenciaException {
          for (int i = 0; i < list.size(); i++) {            
            inserirParticipante(idProj, Integer.parseInt( list.get(i).toString()));
        }
    }
    
    
    private void inserirParticipante(int idProjx, int idParticipante) {

        System.out.println(" ID PROJETO: " + idProjx + " | idParticipante: " + idParticipante);

        try {
            String query = "INSERT INTO " + PARTICIPANTE + " (id_usuario, id_proj) VALUES (?,?) ";
            PreparedStatement stmt;
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idParticipante);
            stmt.setInt(2, idProjx);

            System.out.println("******************** QUERY -> " + query);

            int retorno = stmt.executeUpdate();

            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: o participante com id:"+idParticipante+" não foi cadastrado no banco de dados");
            } else {
                System.out.println("RETORNO TRUE -> BD: o participante foi cadastrado no banco de dados");
            }
        } catch (SQLException sqle) {
            throw new PersistenciaException("Não foi possível cadastrar o participante com id:"+idParticipante+" no banco de dados!", sqle);
        }
    }

    @Override
    public ArrayList<Professor> getParticProfessores(int id) throws PersistenciaException {
        String query = "SELECT usuario.id_usuario FROM usuario "
                + " INNER JOIN usuario_papel ON usuario.id_usuario = usuario_papel.id_usuario "
                + " INNER JOIN papel ON papel.id_papel = usuario_papel.id_papel AND papel.descricao = 'PROFESSOR' "
                + " INNER JOIN participante ON participante.id_usuario = usuario.id_usuario  AND participante.id_proj = " + id;

        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        ArrayList<Professor> listaRetorno = new ArrayList<Professor>();
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Professor a = new Professor();
                a.setId(resultSet.getInt("id_usuario"));
                listaRetorno.add(a);
            }
            return listaRetorno;
        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível listar os professores (participantes) do projeto id: " + id, ex);
        }
    }

    @Override
    public ArrayList<Externo> getParticExternos(int id) throws PersistenciaException {
        String query = "SELECT usuario.id_usuario FROM usuario "
                + " INNER JOIN usuario_papel ON usuario.id_usuario = usuario_papel.id_usuario "
                + " INNER JOIN papel ON papel.id_papel = usuario_papel.id_papel AND papel.descricao = 'EXTERNO' "
                + " INNER JOIN participante ON participante.id_usuario = usuario.id_usuario  AND participante.id_proj = " + id;

        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        ArrayList<Externo> listaRetorno = new ArrayList<Externo>();
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Externo a = new Externo();
                a.setId(resultSet.getInt("id_usuario"));
                listaRetorno.add(a);
            }
            return listaRetorno;
        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível listar os Externos (participantes) do projeto id: " + id, ex);
        }
    }

    @Override
    public ArrayList<Custo> getCustos(int idProj) throws PersistenciaException {
        String query = "SELECT * FROM " + CUSTO + " WHERE id_proj = " + idProj;

        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        ArrayList<Custo> listaRetorno = new ArrayList<Custo>();
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Custo c = new Custo();
                c.setId(resultSet.getInt("id"));
                c.setTipoCusto(TipoCusto.fromString(resultSet.getString("tipo")));
                c.setDescricao(resultSet.getString("descricao"));
                c.setValor(resultSet.getDouble("valor"));
                listaRetorno.add(c);
            }
            return listaRetorno;
        } catch (Exception ex) {
            System.out.println("ERRO: "+ex.getCause()+" ->message: "+ex.getMessage());
            throw new PersistenciaException("Não foi possível listar os custos do projeto id: " + idProj, ex );
        }

    }

    @Override
    public AreaConhecimento getArea(int idProj) throws PersistenciaException {

        //SELECT area_conhecimento.area, area_conhecimento.id_area FROM area_conhecimento NATURAL JOIN projeto WHERE projeto.id_proj = 25        
        String query = "SELECT area_conhecimento.area, area_conhecimento.id_area FROM area_conhecimento NATURAL JOIN projeto WHERE projeto.id_proj = " + idProj;
        System.out.println("******************** QUERY -> " + query);
        PreparedStatement stmt;
        ResultSet resultSet;
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();
            resultSet.next();
            AreaConhecimento p = new AreaConhecimento();
            p.setId(resultSet.getInt("id_area"));
            p.setArea(resultSet.getString("area"));

            return p;

        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível obter a area de conhecimento do projeto identificado com o ID: " + idProj + " no BD", ex);
        }

    }

    @Override
    public void insereArquivo(int idProj) throws PersistenciaException {

        String query = "UPDATE " + PROJETO + " SET is_arquivo = true WHERE id_proj = ?";

        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idProj);

            int retorno = stmt.executeUpdate();

            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: o arquivo do projeto NÃO foi alterado");
            } else {
                System.out.println("RETORNO TRUE -> BD: o arquivo do projeto foi alterado com sucesso");
            }

        } catch (SQLException sqle) {

            throw new PersistenciaException("o arquivo do projeto NÃO foi alterado! , ERRO:", sqle);
        }

    }

    @Override
    public void removeArquivo(int idProj) throws PersistenciaException {
        String query = "UPDATE " + PROJETO + " SET is_arquivo = false WHERE id_proj = ?";

        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idProj);

            int retorno = stmt.executeUpdate();

            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: o arquivo do projeto NÃO foi alterado");
            } else {
                System.out.println("RETORNO TRUE -> BD: o arquivo do projeto foi alterado com sucesso");
            }

        } catch (SQLException sqle) {

            throw new PersistenciaException("o arquivo do projeto NÃO foi alterado! , ERRO:", sqle);
        }
    }

    @Override
    public void atualizaCustos(int idProj, ArrayList<Custo> c) throws PersistenciaException {

        String query = "DELETE FROM " + CUSTO + " WHERE id_proj = ?";
        PreparedStatement stmt;
        System.out.println("************* QUERY: " + query);

        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idProj);
            int retorno = stmt.executeUpdate();
            inserirCustos(c);
            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: os custos do projeto NÃO foram removidos");
            } else {
                System.out.println("RETORNO TRUE -> BD: os custos do projeto foram removidos com sucesso");
            }

        } catch (SQLException sqle) {

            throw new PersistenciaException("os custos do projeto foram removidos , ERRO:", sqle);
        }
    }

    @Override
    public void atualizaParticipantes(int idProj, ArrayList<String> u) throws PersistenciaException {
        
        System.out.println("&&&&&&&&&&&&&&&&&&& CAIU MÉTODO ATUALIZA PARTICIPANTES");
        
        String query = "DELETE FROM " + PARTICIPANTE + " WHERE id_proj = ?";
        PreparedStatement stmt;        
        
        System.out.println("idProj: "+idProj+" tamanho ArrayList<Usuario>.size(): "+u.size());
        System.out.println("************* QUERY: " + query);
        
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idProj);
            int retorno = stmt.executeUpdate();
            
            inserirParticipantes(idProj, u);
            
            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: os participantes do projeto NÃO foram removidos");
            } else {
                System.out.println("RETORNO TRUE -> BD: os participantes do projeto foram removidos com sucesso");
            }

        } catch (SQLException sqle) {

            throw new PersistenciaException("os participantes do projeto foram removidos , ERRO:", sqle);
        }
    }

    

}
