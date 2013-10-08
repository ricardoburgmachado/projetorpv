/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.AreaConhecimento;
import br.com.model.Arquivo;
import br.com.model.Campus;
import br.com.model.Edital;
import br.com.model.Inscricao;
import br.com.model.ProReitor;
import br.com.model.Projeto;
import br.com.model.StatusProjeto;
import br.com.model.TipoProjeto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class DBEdital implements EditalDAO {

    private PostgresConnectionFactory factory;

    public DBEdital(PostgresConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void adiciona(Edital edital) throws PersistenciaException {

        String sql = "insert into edital (titulo, id_usuario, tipo_edital, prazo_inicial, prazo_final, id_arquivo) values (?,?,?,?,?,?)";

        Connection conn = factory.createConnection();
        PreparedStatement stmt;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, edital.getTitulo());
            stmt.setInt(2, edital.getProReitor().getId());
            stmt.setString(3, edital.getTipo().toString());
            System.out.println("***************** ARQUIVO EDITAL: " + edital.getArquivo());
            stmt.setDate(4, new Date(edital.getPrazoInicial().getTime()));
            stmt.setDate(5, new Date(edital.getPrazoFinal().getTime()));

            if (edital.getArquivo() != null) {
                stmt.setInt(6, adicionaArquivo(edital.getArquivo()));
            } else {
                stmt.setInt(6, 0);
            }
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao configurar inserção do edital!", sqle);
        }

        try {
            stmt.execute();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao inserir edital!", sqle);
        } finally {

            factory.close(conn);
        }

    }

    @Override
    public int adicionaArquivo(Arquivo arquivo) throws PersistenciaException {

        if (arquivo == null) {

            return 0;
        }

        String sql = "insert into arquivo (nome_arquivo, extensao, dados) values (?, ?, ?)";
        Connection conn = factory.createConnection();
        PreparedStatement stmt;

        try {

            stmt = conn.prepareStatement(sql);
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

            factory.close(conn);
        }

        return getMaxIDArquivo();
    }

    private int getMaxIDArquivo() throws PersistenciaException {

        String sql = "select max(id_arquivo) as id from arquivo";
        Connection conn = factory.createConnection();
        PreparedStatement stmt;

        try {

            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return rs.getInt("id");
            }
        } catch (SQLException sqle) {
            throw new PersistenciaException("Falha ao obter id máximo de arquivo!", sqle);
        }

        return 0;
    }

    @Override
    public Edital obtem(int idEdital, int idResponsavel) throws PersistenciaException {

        String sql = "select * from edital natural join usuario where id_edital=? and id_usuario=?";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        ResultSet result;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEdital);
            stmt.setInt(2, idResponsavel);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta para obtenção do edital!", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao consultar pela edital!", sqle);
        } finally {

            this.factory.close(conn);
        }

        return nextEdital(result, true);
    }

    @Override
    public Edital obtem(int idEdital) throws PersistenciaException {

        String sql = "select * from edital natural join usuario where id_edital=?";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        ResultSet result;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEdital);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta para obtenção do edital!", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao consultar pela edital!", sqle);
        } finally {

            this.factory.close(conn);
        }

        return nextEdital(result, true);
    }

    private Edital nextEdital(ResultSet result, boolean carregaProReitor) {

        try {

            if (result.next()) {

                Edital edital = createEdital(result);

                if (carregaProReitor) {

                    ProReitor proReitor = new ProReitor();
                    proReitor.setId(result.getInt("id_usuario"));
                    proReitor.setNome(result.getString("nome"));
                    proReitor.setCampus(Campus.valueOf(result.getString("campus")));
                    proReitor.setArea(result.getString("area"));
                    edital.setProReitor(proReitor);
                }

                return edital;
            }
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao obter dados do edital!", sqle);
        }

        return null;
    }

    @Override
    public Arquivo obtemArquivo(int idEdital) throws PersistenciaException {

        String sql = "select * from arquivo where id_arquivo in (select id_arquivo from edital where id_edital=?)";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        ResultSet result;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEdital);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao configurar consulta por arquivo!", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao consultar pelo arquivo!", sqle);
        } finally {

            this.factory.close(conn);
        }

        return nextArquivo(result);
    }

    private Arquivo nextArquivo(ResultSet result) throws PersistenciaException {

        try {

            if (result.next()) {

                Arquivo arquivo = new Arquivo(result.getInt("id_arquivo"), result.getString("nome_arquivo"), result.getString("extensao"), result.getBytes("dados"));
                return arquivo;
            }
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao acessar os dados do arquivo!", sqle);
        }

        return null;
    }

    @Override
    public boolean exists(int idEdital) throws PersistenciaException {

        String sql = "select id_edital from edital where id_edital=?";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        ResultSet result;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEdital);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao configurar consulta por edital!", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao buscar por edital!", sqle);
        } finally {

            this.factory.close(conn);
        }

        return !isEmpty(result);
    }

    private boolean isEmpty(ResultSet result) throws PersistenciaException {

        try {

            return result.getRow() <= 0 && !result.isBeforeFirst();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao acessar resultados da consulta!", sqle);
        }
    }

    @Override
    public boolean exclui(int idEdital, int idResponsavel) throws PersistenciaException {

        String sql = "delete from edital where id_edital=? and id_usuario=?";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEdital);
            stmt.setInt(2, idResponsavel);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar exclusão do edital!", sqle);
        }

        try {

            return stmt.executeUpdate() > 0;
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao excluir edital!", sqle);
        } finally {

            this.factory.close(conn);
        }
    }

    private boolean inscreveProjetoEdital(int idProjeto, int idEdital) throws PersistenciaException {

        String sql = "insert into inscricao (id_proj, id_edital) values (?, ?)";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEdital);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao configurar inscrição do projeto no edital!", sqle);
        }

        try {

            return stmt.executeUpdate() > 0;
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao registrar inscrição do projeto no edital!", sqle);
        } finally {

            this.factory.close(conn);
        }
    }

    @Override
    public void inscreveProjetoEdital(Inscricao inscricao) throws PersistenciaException {

        int idArquivo = adicionaArquivo(inscricao.getArquivo());
        inscreveProjetoEdital(inscricao.getProjeto().getId(), inscricao.getEdital().getId());
        atualizaStatusProjeto(inscricao.getProjeto().getId(), StatusProjeto.INSCRITO);

        if (idArquivo > 0) {

            String sql = "update inscricao set id_arquivo=? where id_proj=? and id_edital=?";
            Connection conn = this.factory.createConnection();
            PreparedStatement stmt;

            try {

                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idArquivo);
                stmt.setInt(2, inscricao.getProjeto().getId());
                stmt.setInt(3, inscricao.getEdital().getId());
            } catch (SQLException sqle) {

                throw new PersistenciaException("Falha ao configurar inserção do arquivo na inscrição", sqle);
            }

            try {

                stmt.executeUpdate();
            } catch (SQLException sqle) {

                throw new PersistenciaException("Falha ao inserir arquivo na inscrição!", sqle);
            } finally {

                this.factory.close(conn);
            }
        }
    }

    private boolean atualizaStatusProjeto(int idProjeto, StatusProjeto status) {

        String sql = "update projeto set status=? where id_proj = ?";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status.toString());
            stmt.setInt(2, idProjeto);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar atualização de status para o projeto!", sqle);
        }

        try {

            return stmt.executeUpdate() > 0;
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao atualizar status do projeto!", sqle);
        } finally {

            this.factory.close(conn);
        }
    }

    @Override
    public boolean verificaInscricao(int idEdital, int idProjeto) throws PersistenciaException {

        String sql = "select id_edital from inscricao where id_edital=? and id_proj=?";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEdital);
            stmt.setInt(2, idProjeto);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta por inscrição!", sqle);
        }

        try {

            return stmt.executeQuery().next();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao consultar inscrição!", sqle);
        } finally {

            this.factory.close(conn);
        }
    }

    @Override
    public List<Edital> listarEditais(int idResponsavel) throws PersistenciaException {

        String sql = "select * from edital where id_usuario = ?";

        PreparedStatement stmt = null;
        ResultSet result;

        try {
            Connection connection = factory.createConnection();

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idResponsavel);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("ERRO : " + ex);
            throw new PersistenciaException("Falha ao realizar consulta", ex);
        }

        return carregaEditais(result);

    }

    private List<Edital> carregaEditais(ResultSet result) {

        List<Edital> editais = new ArrayList<Edital>();

        if (verifyResult(result)) {

            try {
                do {
                    Edital ed = new Edital();
                    ed.setId(result.getInt("id_edital"));
                    ed.setTitulo(result.getString("titulo"));
                    ed.setTipo(TipoProjeto.fromString(result.getString("tipo_edital")));
                    ed.setPrazoInicial(result.getDate("prazo_inicial"));
                    ed.setPrazoFinal(result.getDate("prazo_final"));
                    editais.add(ed);
                } while (result.next());
            } catch (SQLException ex) {

                throw new PersistenciaException("Erro ao acessar propriedades do projeto", ex);
            }
        }

        return editais;
    }

    private boolean verifyResult(ResultSet result) throws PersistenciaException {

        try {
            return result != null && result.next();
        } catch (SQLException ex) {

            throw new PersistenciaException("Não houve resultados!", ex);
        }
    }

    @Override
    public List<Arquivo> obterRetificacoes(int idEdital) throws PersistenciaException {

        String sql = "select * from arquivo inner join retificacao using(id_arquivo) where id_edital=?";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        ResultSet result;
        List<Arquivo> arquivos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEdital);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta por retificações!", sqle);
        }

        try {
            result = stmt.executeQuery();
            Arquivo arquivo = nextArquivo(result);

            while (arquivo != null) {

                arquivos.add(arquivo);
                arquivo = nextArquivo(result);
            }
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao consultar por retificações!", ex);
        } finally {

            this.factory.close(conn);
        }

        return arquivos;
    }

    @Override
    public List<Edital> listarEditais(java.util.Date data, TipoProjeto tipo) throws PersistenciaException {

        String sql = "select * from edital where ? between prazo_inicial and prazo_final and tipo_edital=?";
        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        ResultSet result;
        List<Edital> editais = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new Date(data.getTime()));
            stmt.setString(2, tipo.toString());
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta por editais!", sqle);
        }

        try {
            result = stmt.executeQuery();
            Edital edital = nextEdital(result, false);

            while (edital != null) {

                editais.add(edital);
                edital = nextEdital(result, false);
            }
        } catch (SQLException ex) {

            throw new PersistenciaException("Falha ao consultar por editais!", ex);
        } finally {

            this.factory.close(conn);
        }

        return editais;
    }

    @Override
    public void edita(Edital edital) throws PersistenciaException {

        String sql = "UPDATE edital SET titulo=?, prazo_inicial=?, prazo_final=? WHERE id_edital=? ";
        System.out.println("******************** QUERY edição edital -> " + sql);
        Connection conn = factory.createConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, edital.getTitulo());
            stmt.setDate(2, new Date(edital.getPrazoInicial().getTime()));
            stmt.setDate(3, new Date(edital.getPrazoFinal().getTime()));
            stmt.setInt(4, edital.getId());
            retifica(adicionaArquivo(edital.getArquivo()), edital.getId());
        } catch (SQLException sqle) {
            System.out.println("************** ERRO: " + sqle);
            throw new PersistenciaException("Falha ao configurar edição do edital!", sqle);
        }
        try {
            System.out.println("**************** CAIU DENTRO EDITA DB -> retorno: " + stmt.executeUpdate());
        } catch (SQLException sqle) {
            System.out.println("************** ERRO: " + sqle);
            throw new PersistenciaException("Falha ao editar edital!", sqle);
        } finally {

            factory.close(conn);
        }

    }

    /**
     * Método utilizado na edição de um edital
     *
     * @param arquivo
     * @param idEdital
     * @throws PersistenciaException
     */
    @Override
    public void adicionaArquivo(Arquivo arquivo, int idEdital) throws PersistenciaException {

        String sql = "insert into arquivo (nome_arquivo, extensao, dados, id_edital) values (?, ?, ?, ?)";
        Connection conn = factory.createConnection();
        PreparedStatement stmt;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, arquivo.getNomeArquivo());
            stmt.setString(2, arquivo.getExtensao());
            stmt.setBytes(3, arquivo.getDados());
            stmt.setInt(4, idEdital);
        } catch (SQLException sqle) {
            throw new PersistenciaException("Falha ao configurar inserção do arquivo!", sqle);
        }
        try {
            stmt.execute();
        } catch (SQLException sqle) {
            throw new PersistenciaException("Falha ao inserir arquivo(Edição de edital)!", sqle);
        } finally {
            factory.close(conn);
        }
    }

    @Override
    public void retifica(int idArquivo, int idEdital) {

        String sql = "insert into retificacao (id_arquivo, id_edital) values (?,?)";
        Connection conn = factory.createConnection();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idArquivo);
            stmt.setInt(2, idEdital);

        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao configurar retificação do edital!", sqle);
        }

        try {

            stmt.execute();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao retificar edital!", sqle);
        } finally {

            factory.close(conn);
        }
    }

    @Override
    public Inscricao obtemInscricao(int idProjeto, int idEdital) throws PersistenciaException {

        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        ResultSet result;
        String sql = "select *, edital.titulo as titulo_edital, projeto.titulo as titulo_projeto from inscricao inner join edital using (id_edital) inner join projeto using (id_proj) inner join area_conhecimento using (id_area) where id_proj = ? and id_edital = ?";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEdital);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta por inscrição!", sqle);
        }

        try {

            result = stmt.executeQuery();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao consultar por inscrição!", sqle);
        } finally {

            this.factory.close(conn);
        }

        return nextInscricao(result, false);
    }

    private Inscricao nextInscricao(ResultSet result, boolean carregaArquivo) {

        try {

            if (result.next()) {

                Projeto projeto = createProjeto(result);
                Edital edital = createEdital(result);

                if (carregaArquivo) {

                    return new Inscricao(projeto, edital, createArquivo(result));
                } else {

                    return new Inscricao(projeto, edital);
                }
            }
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao obter dados do edital!", sqle);
        }

        return null;
    }

    private Projeto createProjeto(ResultSet result) throws SQLException {

        Projeto projeto = new Projeto();
        projeto.setId(result.getInt("id_proj"));
        projeto.setAreaConhecimento(new AreaConhecimento(result.getString("area")));
        projeto.setPalavrasChave(result.getString("palavras_chave"));
        projeto.setResumo(result.getString("resumo"));
        projeto.setStatus(StatusProjeto.valueOf(result.getString("status")));

        try {

            projeto.setTitulo(result.getString("titulo_projeto"));
        } catch (SQLException sqle) {

            projeto.setTitulo(result.getString("titulo"));
        }

        return projeto;
    }

    private Edital createEdital(ResultSet result) throws SQLException {

        Edital edital = new Edital();
        edital.setId(result.getInt("id_edital"));
        edital.setPrazoFinal(result.getDate("prazo_final"));
        edital.setPrazoInicial(result.getDate("prazo_inicial"));
        edital.setTipo(TipoProjeto.valueOf(result.getString("tipo_edital")));

        try {

            edital.setTitulo(result.getString("titulo_edital"));
        } catch (SQLException sqle) {

            edital.setTitulo(result.getString("titulo"));
        }

        return edital;
    }

    @Override
    public void excluiInscricao(int idProjeto, int idEdital) throws PersistenciaException {

        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        String sql = "delete from inscricao where id_proj=? and id_edital=?";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEdital);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar exclusão de inscrição!", sqle);
        }

        try {

            stmt.executeUpdate();
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao excluir inscrição!", sqle);
        }
    }

    @Override
    public List<Inscricao> listarInscricoes(int idUsuario) throws PersistenciaException {

        Connection conn = this.factory.createConnection();
        PreparedStatement stmt;
        ResultSet result;
        List<Inscricao> inscricoes = new ArrayList<>();
        String sql = "select *, edital.titulo as titulo_edital, projeto.titulo as titulo_projeto, inscricao.id_arquivo as ident_arquivo from inscricao inner join edital using (id_edital) inner join projeto using (id_proj) inner join area_conhecimento using (id_area) left outer join arquivo on inscricao.id_arquivo = arquivo.id_arquivo where id_responsavel = ?";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao preparar consulta por inscrições!", sqle);
        }

        try {

            result = stmt.executeQuery();
            Inscricao inscricao;
            
            while ((inscricao = nextInscricao(result, true)) != null) {
                
                inscricoes.add(inscricao);
            }
        } catch (SQLException sqle) {

            throw new PersistenciaException("Falha ao consultar por inscrições!", sqle);
        } finally {

            this.factory.close(conn);
        }

        return inscricoes;
    }

    private Arquivo createArquivo(ResultSet result) throws SQLException {

        Arquivo arquivo = new Arquivo(result.getString("nome_arquivo"), result.getString("extensao"), result.getBytes("dados"));

        try {

            arquivo.setIdArquivo(result.getInt("id_arquivo"));
        } catch (SQLException sqle) {

            arquivo.setIdArquivo(result.getInt("ident_arquivo"));
        }

        return arquivo;

    }
}
