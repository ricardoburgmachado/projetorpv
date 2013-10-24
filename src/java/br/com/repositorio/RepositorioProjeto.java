/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.AutorizacaoException;
import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import Exceptions.PrivacidadeException;
import br.com.dao.ProjetoDAO;
import br.com.model.Aluno;
import br.com.model.AreaConhecimento;
import br.com.model.Custo;
import br.com.model.Externo;
import br.com.model.Permissao;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.Recado;
import br.com.model.StatusProjeto;
import br.com.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public int inserir(Projeto p) throws PersistenciaException, DadoInconsistenteException {

        DadoInconsistenteException exception = null;

        if (verificaAreaConhecimentoInvalida(p.getAreaConhecimento())) {

            exception = new DadoInconsistenteException(exception, "Área de conhecimento não informada <br/>");
        }

        if (verificaNulo(p.getTipoProjeto())) {

            exception = new DadoInconsistenteException(exception, "Tipo de projeto não informado <br/>");
        }

        if (verificaVazio(p.getTitulo())) {

            exception = new DadoInconsistenteException(exception, "Título não informado <br/>");
        }
        
        if (verificaNulo(p.getInicio())) {
            exception = new DadoInconsistenteException(exception, "Data inicio não informada!<br/>");
        }

        if (!verificaNulo(p.getInicio()) && !verificaNulo(p.getFim())) {
            if (verificaPrazoMenor(p.getInicio(), p.getFim())) {
                exception = new DadoInconsistenteException(exception, "Data fim menor que data inicio!<br/>");
            }
        }
        
        if (verificaNulo(p.getFim())) {
            exception = new DadoInconsistenteException(exception, "Data fim não informado!<br/>");
        }


        if (exception == null) {

            return projDAO.inserir(p);
        } else {

            throw exception;
        }

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

    public Projeto obter(int id, Usuario usuario) throws PersistenciaException, AutorizacaoException {

        if (usuario == null || !usuario.getPermissoes().contains(Permissao.CRUD_PROJETO)) {

            throw new AutorizacaoException("Usuário sem permissão para visualizar projeto!");
        }

        return projDAO.obter(id);
    }

    public Projeto obter(int id) throws PersistenciaException {

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
        
        if (verificaVazio(p.getTitulo())) {

            throw new DadoInconsistenteException("Título não informado <br/>");
        }

        projDAO.editar(p);
        projDAO.atualizaCustos(p.getId(), p.getCustos());
        projDAO.atualizaParticipantes(p.getId(), p.getParticipantesString());
    }
    
    public void editaProjetoEmExecucao(Projeto projeto, Usuario usuario)  throws DadoInconsistenteException, PrivacidadeException, AutorizacaoException, PersistenciaException{
        
        if(verificaConsistenciaEditaProjetoExec(projeto, usuario)){
            
            this.projDAO.editar(projeto);
            this.atualizaParticipantes(projeto.getId(), this.projDAO.carregaParticipantes(projeto.getId()), projeto.getParticipantes());
        }
    }
    
    private void atualizaParticipantes(int idProjeto, Collection<Usuario> antigosParticips, Collection<Usuario> novosParticips){
        
        Set<Usuario> antigos = new HashSet<>(antigosParticips);
        antigos.removeAll(novosParticips);
        this.projDAO.removeParticipantes(idProjeto, antigos);
        
        Set<Usuario> novos  = new HashSet<>(novosParticips);
        novos.removeAll(antigosParticips);
        this.projDAO.adicionaParticipantes(idProjeto, novos);
    }

    private boolean verificaConsistenciaEditaProjetoExec(Projeto projeto, Usuario usuario) throws DadoInconsistenteException, PrivacidadeException, AutorizacaoException {

        DadoInconsistenteException diex = null;

        if (usuario == null || !usuario.getPermissoes().contains(Permissao.CRUD_PROJETO)) {

            throw new AutorizacaoException("Usuário sem permissão para editar projeto!");
        }

        if (projeto == null) {

            throw new DadoInconsistenteException("Projeto não informado!");
        }

        if (projeto.getProfessor().getId() != usuario.getId()) {

            throw new PrivacidadeException("Projeto não pertencente ao usuário!");
        }

        if (projeto.getResumo() == null || projeto.getResumo().equals("")) {

            diex = new DadoInconsistenteException("Resumo não informado!");
        }

        if (projeto.getPalavrasChave() == null || projeto.getPalavrasChave().equals("")) {

            diex = new DadoInconsistenteException(diex, "Palavras-chave não informadas!");
        }

        if (diex != null) {

            throw diex;
        }

        return true;
    }

    public List<Projeto> listarProjetos(int idResponsavel) {

        List<Projeto> projetos = this.projDAO.listarProjetos(idResponsavel);

        for (Projeto proj : projetos) {

            this.projDAO.carregaCustos(proj);
            proj.setParticipantes(this.projDAO.carregaParticipantes(idResponsavel));
        }

        return projetos;
    }

    public List<Projeto> filtrarProjetos(Usuario usuario, Set<StatusProjeto> status) throws PersistenciaException, AutorizacaoException {

        if (verificaConsistenciaParaFiltragem(usuario, status)) {

            return this.projDAO.listarProjetos(usuario.getId(), status);
        }

        return null;
    }

    private boolean verificaConsistenciaParaFiltragem(Usuario usuario, Set<StatusProjeto> status) throws AutorizacaoException, DadoInconsistenteException {

        if (!usuario.getPermissoes().contains(Permissao.CRUD_PROJETO) && !usuario.getPermissoes().contains(Permissao.INSCRICAO_EDITAL)) {

            throw new AutorizacaoException("Usuário sem permissão para listagem!");
        }

        if (status == null || status.isEmpty()) {

            throw new DadoInconsistenteException("Conjunto de estados para filtragem não informado!");
        }

        return true;
    }

    public List<Projeto> listarProjetosSubmetidosHomologacao(Usuario usuario, Set<StatusProjeto> status) {

        if (verificaConsistenciaListagemSubmetidos(usuario)) {

            return this.projDAO.listarProjetos(usuario.getId(), status, usuario.getCampus(), usuario.getArea());
        }

        return null;
    }

    private boolean verificaConsistenciaListagemSubmetidos(Usuario usuario) throws AutorizacaoException {

        if (usuario == null || !usuario.getPermissoes().contains(Permissao.LISTAGEM_SUBMETIDOS)) {

            throw new AutorizacaoException("Usuário sem permissão para listagem dos projetos submetidos!");
        }

        return true;
    }

    public void submeterHomologacao(int idProjeto, Usuario usuario) throws PersistenciaException, DadoInconsistenteException {

        Projeto projeto = obter(idProjeto, usuario);

        submeterHomologacao(projeto, usuario.getId());
    }

    public void submeterHomologacao(Projeto projeto, int idResponsavel) throws PersistenciaException, DadoInconsistenteException {

        DadoInconsistenteException exception = verificaConsistenciaParaSubmissao(projeto, idResponsavel);

        if (exception != null) {

            throw exception;
        } else {

            projeto.setStatus(StatusProjeto.SUBMETIDO_HOMOLOGACAO);
            this.projDAO.atualizaStatus(projeto);
        }
    }

    protected void atualizaStatus(Projeto projeto) throws PersistenciaException {

        this.projDAO.atualizaStatus(projeto);
    }

    private DadoInconsistenteException verificaConsistenciaParaSubmissao(Projeto projeto, int idUsuario) throws PersistenciaException {

        DadoInconsistenteException exception = null;

        try {

            verificaCamposNaoInformados(projeto);
        } catch (DadoInconsistenteException diex) {

            exception = diex;
        }

        if (!verificaProjetoResponsavel(projeto, idUsuario)) {

            exception = new PrivacidadeException(exception, "Projeto não pertencente ao professor");
        }

        try {

            verificaStatusParaSubmissaoHomologacao(projeto.getStatus());
        } catch (DadoInconsistenteException diex) {

            if (exception == null) {

                exception = diex;
            } else {

                exception.setException(diex);
            }
        }

        return exception;
    }

    private boolean verificaProjetoResponsavel(Projeto projeto, int idResponsavel) {

        return !verificaNulo(projeto) && !verificaNulo(projeto.getProfessor()) && projeto.getProfessor().getId() == idResponsavel;

    }

    private void verificaStatusParaSubmissaoHomologacao(StatusProjeto status) throws DadoInconsistenteException {

        if (status == null || status != StatusProjeto.CRIADO) {

            throw new DadoInconsistenteException("O status do projeto deve ser " + StatusProjeto.CRIADO);
        }
    }

    private void verificaCamposNaoInformados(Projeto projeto) throws DadoInconsistenteException {

        DadoInconsistenteException exception = null;

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

            exception = new DadoInconsistenteException(exception, "Resumo não informado!");
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

        if (verificaNulo(projeto.getProfessor())) {

            exception = new DadoInconsistenteException(exception, "Deve haver professor associado ao projeto!");
        }

        if (!projeto.getArquivo()) {

            exception = new DadoInconsistenteException(exception, "Dave haver arquivo associado ao projeto!");
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

    private boolean verificaAreaConhecimentoInvalida(AreaConhecimento area) {

        return verificaNulo(area) || area.getId() <= 0;
    }

    public void excluir(int idProj) {

        DadoInconsistenteException exception = null;

        if (checaStatus(idProj)) {
            exception = new DadoInconsistenteException(exception, "Este projeto já foi submetido, sendo impossível de ser excluído <br/>");
        }

        if (exception == null) {
            this.projDAO.excluir(idProj);
        } else {
            throw exception;
        }
    }
    
    protected void addRecado(Projeto projeto, Recado recado) throws PersistenciaException{
        
        this.projDAO.addRecado(projeto.getId(), recado);
    }
    
    protected List<Recado> listarRecados(int idProjeto) throws PersistenciaException{
        
        return this.projDAO.listarRecados(idProjeto);
    }

    /**
     * Método utilizado para auxiliar na exclusão de um projeto, verificando se
     * o mesmo está com o status == "CRIADO" para então poder excluir o projeto
     *
     * @param id do projeto
     * @return FALSE caso NÃO possa ser excluído (status == 'CRIADO')
     * @return TRUE caso possa ser excluído (status != 'CRIADO')
     */
    private boolean checaStatus(int idProj) {

        String status = this.projDAO.consultaStatus(idProj);
        if (status.equalsIgnoreCase("CRIADO")) {
            return false;
        } else {
            return true;
        }
    }
    
    protected void atualizaStatusProjeto(Projeto projeto){
        
        this.projDAO.atualizaStatus(projeto);
    }
    /**
     * Método utilizado para alterar o status (feito pelo Pro-reitor)
     * @param p 
     */
    public void alteraStatusProjeto(Projeto p){
         
         DadoInconsistenteException exception = null;


        if (verificaNulo(p.getStatus())) {
            exception = new DadoInconsistenteException(exception, "Status do projeto não informado <br/>");
        }

        if (p.getRespaldo() == null) {
            exception = new DadoInconsistenteException(exception, "Arquivo respaldo não anexado <br/>");
        }

        if (exception == null) {
            projDAO.alteraStatus(p);            
        } else {
            throw exception;
        }

    }
    
    public void prestaContas(Projeto projeto){
         DadoInconsistenteException exception = null;


        if (projeto.getPrestacaoConta() == null) {
            exception = new DadoInconsistenteException(exception, "Arquivo não anexado <br/>");
        }

        if (exception == null) {
            projDAO.prestaContas(projeto);
        } else {
            throw exception;
        }
  
     }
  
     private boolean verificaPrazoMenor(Date ini, Date fim) {
        return ini.after(fim);
    }
     
    public List listarProjetoInscritos(String tipoProjeto){
    
         List<Projeto> projetos = this.projDAO.listarProjetosInscritos(tipoProjeto);
         return projetos;    
    }

    public List<Projeto> listarProjetos(){
    
        List<Projeto> projetos = this.projDAO.listarProjetos();
        return projetos;    
    }
}
