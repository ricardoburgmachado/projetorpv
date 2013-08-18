/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import br.com.dao.ProjetoDAO;
import br.com.model.Aluno;
import br.com.model.AreaConhecimento;
import br.com.model.Custo;
import br.com.model.Externo;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.StatusProjeto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class RepositorioProjeto {

    private ProjetoDAO projDAO;

    public RepositorioProjeto() {
    }

    /**
     * Construtor
     *
     * @param projetoDAO
     */
    public RepositorioProjeto(ProjetoDAO pDAO) {

        this.projDAO = pDAO;
    }

    public int inserir(Projeto p) {
        return projDAO.inserir(p);
    }

    public ArrayList<AreaConhecimento> listarAreas() {
        //return usuDao.listar(p);
        return projDAO.listarAreas();
    }

    public void inserirCustos(ArrayList<Custo> c) {
        projDAO.inserirCustos(c);
    }

    public void inserirParticipantes(int idProj, String[] idPart) {
        if (valoresValido(idPart)) {
            projDAO.inserirParticipantes(idProj, idPart);
        }
    }

    public boolean valoresValido(String[] idPart) {
        try {
            for (int i = 0; i < idPart.length; i++) {
                double temp = Integer.parseInt(idPart[i]);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Projeto obter(int id) {

        return projDAO.obter(id);
    }

    public Projeto obter(Projeto p) {

        return projDAO.obter(p);
    }

    public ArrayList<Aluno> getParticAlunos(int id) {
        return projDAO.getParticAlunos(id);
    }

    public ArrayList<Professor> getParticProfessores(int id) {
        return projDAO.getParticProfessores(id);
    }

    public ArrayList<Externo> getParticExternos(int id) {
        return projDAO.getParticExternos(id);
    }

    public ArrayList<Custo> getCustos(int idProj) {
        return projDAO.getCustos(idProj);
    }

    public void insereArquivo(int idProj) {
        projDAO.insereArquivo(idProj);
    }

    public void removeArquivo(int idProj) {
        projDAO.removeArquivo(idProj);
    }

    public void editar(Projeto p) {
        projDAO.editar(p);
        projDAO.atualizaCustos(p.getId(), p.getCustos());
        projDAO.atualizaParticipantes(p.getId(), p.getParticipantesString());
    }

    public List<Projeto> listarProjetos(int idResponsavel) {

        List<Projeto> projetos = this.projDAO.listarProjetos(idResponsavel);

        for (Projeto proj : projetos) {

            this.projDAO.carregaCustos(proj);
            this.projDAO.carregaParticipantes(proj);
        }

        return projetos;
    }

    public void submeterHomologacao(Projeto projeto) throws PersistenciaException, DadoInconsistenteException {

        DadoInconsistenteException exception = null;

        try {

            verificaCamposNaoInformados(projeto);
        } catch (DadoInconsistenteException diex) {

            exception = diex;
        }

        try {
            
            verificaStatusParaSubmissaoHomologacao(projeto.getStatus());
        }catch(DadoInconsistenteException diex){
            
            exception = new DadoInconsistenteException(exception, null);
        }
        
        if(exception != null){
            
            throw exception;
        }else{
            
            this.projDAO.editar(projeto);
        }
    }

    private void verificaStatusParaSubmissaoHomologacao(StatusProjeto status) throws DadoInconsistenteException {

        if (status == null || status != StatusProjeto.CRIADO) {

            throw new DadoInconsistenteException("O status do projeto deve ser CRIADO!");
        }
    }

    private void verificaCamposNaoInformados(Projeto projeto) throws DadoInconsistenteException {

        DadoInconsistenteException exception = null;
        final String VAZIO = "";

        if (projeto == null) {

            throw new DadoInconsistenteException("Projeto Inválido!");
        }

        if (verificaVazio(projeto.getTitulo())) {

            exception = new DadoInconsistenteException(exception, "Título não informado!");
        }

        if (verificaVazio(projeto.getPalavrasChave())) {

            exception = new DadoInconsistenteException(exception, "Palavras-chave não informadas!");
        }

        if (verificaVazio(projeto.getResumo())) {

            exception = new DadoInconsistenteException(exception, "Resumo não informado");
        }

        if (verificaNulo(projeto.getAreaConhecimento())) {

            exception = new DadoInconsistenteException(exception, "Área de conhecimento não informada!");
        }

        if (verificaNulo(projeto.getTipoProjeto())) {

            exception = new DadoInconsistenteException(exception, "Tipo do projeto não informado!");
        }

        if (verificaNulo(projeto.getStatus())) {

            exception = new DadoInconsistenteException(exception, "Status do projeto é nulo!");
        }
        
        if(verificaNulo(projeto.getProfessor())){
            
            exception = new DadoInconsistenteException(exception, "Deve haver professor associado ao projeto!");
        }

        if (exception != null) {

            throw exception;
        }
    }

    private boolean verificaVazio(String campo) {

        return verificaNulo(campo) || campo.equals("");
    }

    private boolean verificaNulo(Object obj) {

        return obj == null;
    }
}
