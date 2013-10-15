/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Aluno;
import br.com.model.AreaConhecimento;
import br.com.model.Campus;
import br.com.model.Custo;
import br.com.model.Externo;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.StatusProjeto;
import br.com.model.TipoCusto;
import br.com.model.TipoProjeto;
import br.com.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

        String query = "INSERT INTO " + PROJETO + " (titulo, palavras_chave, resumo, sigilo, id_area, tipo_proj, status, id_responsavel) "
                + "VALUES (?,?,?,?,?,?,?,?) ";

        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, p.getTitulo());
            stmt.setString(2, p.getPalavrasChave());
            stmt.setString(3, p.getResumo());
            stmt.setBoolean(4, p.getSigilo());
            stmt.setInt(5, p.getAreaConhecimento().getId());
            stmt.setString(6, p.getTipoProjeto().toString());
            stmt.setString(7, "CRIADO");
            stmt.setInt(8, p.getProfessor().getId());

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
            System.out.println("********************* ERRO: " + sqle);
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
            p.setStatus(StatusProjeto.valueOf(resultSet.getString("status")));
            p.setPalavrasChave(resultSet.getString("palavras_chave"));
            p.setSigilo(resultSet.getBoolean("sigilo"));
            p.setArquivo(resultSet.getBoolean("is_arquivo"));
            Professor prof = new Professor();
            prof.setId(resultSet.getInt("id_responsavel"));
            p.setProfessor(prof);
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
        String query = "SELECT usuario.id_usuario, usuario.nome FROM usuario "
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
                a.setNome(resultSet.getString("nome"));
                listaRetorno.add(a);
            }
            return listaRetorno;
        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível listar os alunos (participantes) do projeto id: " + id, ex);
        }
    }

    @Override
    public void editar(Projeto p) throws PersistenciaException {
        String query = "UPDATE " + PROJETO + " SET titulo=?, palavras_chave=?, resumo=?, sigilo=?, id_area=?, tipo_proj=?, id_responsavel=? WHERE id_proj = ?";

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
            stmt.setInt(7, p.getProfessor().getId());
            System.out.println("********************** ID PROFESSOR: " + p.getProfessor().getId());
            stmt.setInt(8, p.getId());
            System.out.println("******************** QUERY -> " + query);
            int retorno = stmt.executeUpdate();

            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: o projeto não foi atualizado no banco de dados");

            } else {
                System.out.println("RETORNO TRUE -> BD: o projeto foi atualizado no banco de dados");

                //inserirCusteios(p.getCusteios());                               
            }

        } catch (SQLException sqle) {

            System.out.println("**************** ERRO: " + sqle.getCause());
            throw new PersistenciaException("Não foi possível atualizar o projeto no banco de dados!, erro: ", sqle);
        }

    }

    @Override
    public void excluir(Projeto p) throws PersistenciaException {
    }

    @Override
    public void excluir(int idProj) throws PersistenciaException {
        String query = "DELETE FROM " + PROJETO + " WHERE id_proj = ?";

        System.out.println("******************** QUERY -> " + query);

        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idProj);

            System.out.println("******************** QUERY -> " + query);
            int retorno = stmt.executeUpdate();

            if (retorno == 0) {
                System.out.println("RETORNO FALSE -> BD: o projeto não foi excluído no banco de dados");

            } else {
                System.out.println("RETORNO TRUE -> BD: o projeto foi excluído no banco de dados");
            }

        } catch (SQLException sqle) {

            System.out.println("**************** ERRO: " + sqle.getCause());
            throw new PersistenciaException("Não foi possível excluir o projeto no banco de dados!, erro: ", sqle);
        }

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
     *
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
     *
     * @param idProj
     * @param list
     * @throws PersistenciaException
     */
    @Override
    public void inserirParticipantes(int idProj, ArrayList<String> list) throws PersistenciaException {
        for (int i = 0; i < list.size(); i++) {
            inserirParticipante(idProj, Integer.parseInt(list.get(i).toString()));
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
                System.out.println("RETORNO FALSE -> BD: o participante com id:" + idParticipante + " não foi cadastrado no banco de dados");
            } else {
                System.out.println("RETORNO TRUE -> BD: o participante foi cadastrado no banco de dados");
            }
        } catch (SQLException sqle) {
            throw new PersistenciaException("Não foi possível cadastrar o participante com id:" + idParticipante + " no banco de dados!", sqle);
        }
    }

    @Override
    public ArrayList<Professor> getParticProfessores(int id) throws PersistenciaException {
        String query = "SELECT usuario.id_usuario, usuario.nome FROM usuario "
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
                a.setNome(resultSet.getString("nome"));
                listaRetorno.add(a);
            }
            return listaRetorno;
        } catch (SQLException ex) {
            throw new PersistenciaException("Não foi possível listar os professores (participantes) do projeto id: " + id, ex);
        }
    }

    @Override
    public ArrayList<Externo> getParticExternos(int id) throws PersistenciaException {
        String query = "SELECT usuario.id_usuario, usuario.nome FROM usuario "
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
                a.setNome(resultSet.getString("nome"));
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
            System.out.println("ERRO: " + ex.getCause() + " ->message: " + ex.getMessage());
            throw new PersistenciaException("Não foi possível listar os custos do projeto id: " + idProj, ex);
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

        System.out.println("idProj: " + idProj + " tamanho ArrayList<Usuario>.size(): " + u.size());
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

    /**
     * Lista todos os projetos associados à um responsável (professor).
     *
     * @param idResponsavel Identificador do responsável.
     * @return Lista de projetos associados à um responsável (professor).
     * @throws PersistenciaException Caso algum problema de persistência ocorra
     */
    @Override
    public List<Projeto> listarProjetos(int idResponsavel) throws PersistenciaException {

        //Busca pelos dados do projeto incluindo seus participantes e o papel de cada participante. Também busca pelo nome e id do responsável pelo projeto (Professor)
        String sql = "select * from projeto inner join usuario on id_responsavel = id_usuario inner join area_conhecimento using (id_area) where id_responsavel=?";

        PreparedStatement stmt = null;
        ResultSet result;

        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idResponsavel);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao realizar consulta", ex);
        }

        return carregaProjetos(result);
    }

    /**
     * Cria uma lista de projetos com base no resultado da consulta. Envolve
     * professor responsável e área de conhecimento.
     *
     * @param result Resultado da consulta.
     * @return Lista de projetos.
     */
    private List<Projeto> carregaProjetos(ResultSet result) {

        List<Projeto> projetos = new ArrayList<Projeto>();

        if (verifyResult(result)) {

            try {
                do {

                    Projeto projeto = new Projeto();
                    projeto.setTitulo(result.getString("titulo"));
                    projeto.setId(result.getInt("id_proj"));
                    projeto.setResumo(result.getString("resumo"));
                    projeto.setSigilo(result.getBoolean("sigilo"));
                    projeto.setTipoProjeto(TipoProjeto.valueOf(result.getString("tipo_proj")));
                    projeto.setAreaConhecimento(new AreaConhecimento(result.getInt("id_area"), result.getString("area")));
                    projeto.setPalavrasChave(result.getString("palavras_chave"));
                    projeto.setArquivo(result.getBoolean("is_arquivo"));
                    projeto.setStatus(StatusProjeto.valueOf(result.getString("status")));
                    Professor professor = new Professor(result.getString("nome"), Campus.valueOf(result.getString("campus")));
                    professor.setId(result.getInt("id_responsavel"));
                    projeto.setProfessor(professor);

                    projetos.add(projeto);
                } while (result.next());
            } catch (SQLException ex) {

                throw new PersistenciaException("Erro ao acessar propriedades do projeto", ex);
            }
        }

        return projetos;
    }

    /**
     * Consulta e atribui os participantes relacionados ao projeto.
     *
     * @param projeto Projeto ao qual seus participantes serão atribuídos
     * @throws PersistenciaException Caso ocorra algum problema com a
     * persistência
     */
    @Override
    public void carregaParticipantes(Projeto projeto) throws PersistenciaException {

        String sql = "select * from projeto natural join participante natural join usuario natural join usuario_papel natural join papel where id_proj=?";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, projeto.getId());
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao preparar consulta!", ex);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao executar consulta!", ex);
        }

        if (verifyResult(result)) {

            try {

                ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
                do {

                    Usuario usuario = Usuario.instantiateUsuario(result.getString("descricao"));
                    System.out.println(result.getString("descricao"));
                    System.out.println(usuario);
                    usuario.setCampus(Campus.valueOf(result.getString("campus")));
                    usuario.setNome(result.getString("nome"));
                    usuario.setId(result.getInt("id_usuario"));
                    usuarios.add(usuario);
                } while (result.next());

                projeto.setParticipantes(usuarios);
            } catch (SQLException ex) {

                throw new PersistenciaException("Falha ao acessar o resultado da consulta", ex);
            }
        }
    }

    /**
     * Consulta e atribui os custos relacionados ao projeto.
     *
     * @param projeto Projeto ao qual seus custos serão atribuídos
     * @throws PersistenciaException Caso ocorra algum problema com a
     * persistência
     */
    @Override
    public void carregaCustos(Projeto projeto) throws PersistenciaException {

        String sql = "select * from custo where id_proj=?";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, projeto.getId());
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao preparar consulta", ex);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao executar consulta", ex);
        }

        if (verifyResult(result)) {
            try {
                ArrayList<Custo> custos = new ArrayList<>();
                do {

                    Custo custo = new Custo();
                    custo.setId(result.getInt("id"));
                    custo.setDescricao(result.getString("descricao"));
                    custo.setTipoCusto(TipoCusto.valueOf(result.getString("tipo")));
                    custo.setValor(result.getDouble("valor"));
                    custos.add(custo);
                } while (result.next());

                projeto.setCustos(custos);
            } catch (SQLException ex) {

                throw new PersistenciaException("Falha ao acessar dados da consulta", ex);
            }
        }
    }

    /**
     * Verifica se a consulta retornou algum resultado. Move o cursor do
     * resultado para a primeira linha.
     *
     * @param result ResultSet
     * @return true se algum resultado foi encontrado
     * @throws PersistenciaException Caso o resultado não seja acessível
     */
    private boolean verifyResult(ResultSet result) throws PersistenciaException {

        try {
            return result != null && result.next();
        } catch (SQLException ex) {

            throw new PersistenciaException("Não houve resultados!", ex);
        }
    }

    @Override
    public void atualizaStatus(Projeto projeto) throws PersistenciaException {

        String sql = "update " + PROJETO + " set status=? where id_proj=?";
        PreparedStatement stmt = null;

        try {

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, projeto.getStatus().toString());
            stmt.setInt(2, projeto.getId());
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar atualização");
        }

        try {

            stmt.executeUpdate();
        } catch (SQLException sqle) {

            System.out.println(sqle);
            throw new PersistenciaException("Falha ao atualizar registro");
        }
    }

    public boolean verificaStatus() {

        return true;
    }

    @Override
    public String consultaStatus(int idProj) {

        String sql = "SELECT status FROM " + PROJETO + " where id_proj=?";
        PreparedStatement stmt = null;
        ResultSet resultSet;
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idProj);
            resultSet = stmt.executeQuery();
            resultSet.next();
            return resultSet.getString(1);

        } catch (SQLException sqle) {
            System.out.println("************** ERRO: " + sql);
            throw new PersistenciaException("Falha ao consultar status do projeto");
        }
    }

    @Override
    public List<Projeto> listarProjetos(int idResponsavel, Set<StatusProjeto> status) throws PersistenciaException {

        StringBuilder sqlBuilder = new StringBuilder("select * from projeto inner join usuario on id_responsavel = id_usuario inner join area_conhecimento using (id_area) where id_responsavel=?");

        if (status.size() > 0) {

            sqlBuilder.append(" and status=?");
            for (int i = 1; i < status.size(); i++) {

                sqlBuilder.append(" or status=?");
            }
        }

        PreparedStatement stmt = null;
        ResultSet result;

        try {

            stmt = connection.prepareStatement(sqlBuilder.toString());
            stmt.setInt(1, idResponsavel);

            int ini = 2;
            Object[] stts = status.toArray();

            for (int i = 0; i < stts.length; i++) {

                stmt.setString(ini + i, stts[i].toString());
            }
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta por projetos!", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao realizar consulta por projetos!", ex);
        }

        return carregaProjetos(result);
    }

    @Override
    public List<Projeto> listarProjetos(int idResponsavel, Set<StatusProjeto> status, Campus campus, TipoProjeto tipo) throws PersistenciaException {

        StringBuilder sqlBuilder = new StringBuilder("select * from projeto inner join usuario on id_responsavel = id_usuario inner join area_conhecimento using (id_area) where campus=? and tipo_proj=?");

        if (status.size() > 0) {

            sqlBuilder.append(" and status=?");
            for (int i = 1; i < status.size(); i++) {

                sqlBuilder.append(" or status=?");
            }
        }

        PreparedStatement stmt;
        ResultSet result;

        try {

            stmt = connection.prepareStatement(sqlBuilder.toString());
            stmt.setString(1, campus.toString());
            stmt.setString(2, tipo.toString());

            int ini = 3;
            Object[] stts = status.toArray();

            for (int i = 0; i < stts.length; i++) {

                stmt.setString(ini + i, stts[i].toString());
            }
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta por projetos!", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao realizar consulta por projetos!", ex);
        }

        return carregaProjetos(result);
    }
}
