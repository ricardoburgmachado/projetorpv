/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.AutorizacaoException;
import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import Exceptions.PrivacidadeException;
import br.com.model.AreaConhecimento;
import br.com.model.Arquivo;
import br.com.model.Edital;
import br.com.model.Inscricao;
import br.com.model.Projeto;
import br.com.model.Recado;
import br.com.model.StatusProjeto;
import br.com.model.Usuario;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafael
 */
public class RepositorioFacade {
    
    private RepositorioUsuario repUsuario;
    private RepositorioProjeto repProjeto;
    private RepositorioEdital repEdital;
    
    public RepositorioFacade() {
        
        RepositorioPostgresFactory repositorioFactory = new RepositorioPostgresFactory();
        this.repUsuario = repositorioFactory.createRepositorioUsuario();
        this.repProjeto = repositorioFactory.createRepositorioProjeto();
        this.repEdital = repositorioFactory.createRepositorioEdital();
    }
    
    public void inscreveEdital(int idEdital, int idProjeto, Arquivo arquivo, Date dataInscricao, Usuario usuario) throws PersistenciaException, DadoInconsistenteException, PrivacidadeException, AutorizacaoException {
        
        Edital edital = repEdital.obtemEdital(idEdital);
        Projeto projeto = repProjeto.obter(idProjeto);
        Inscricao inscricao = edital.inscreveProjeto(projeto, arquivo, dataInscricao);
        
        this.repEdital.inscreveProjetoEdital(inscricao, usuario);
    }
    
    public Usuario autenticaUsuario(String login, String senha) throws PersistenciaException {
        
        return this.repUsuario.autenticaUsuario(login, senha);
    }
    
    public Edital obter(int idEdital, Usuario usuario) throws PersistenciaException, PrivacidadeException, AutorizacaoException, DadoInconsistenteException {
        
        return repEdital.obtemEdital(idEdital, usuario);
    }
    
    public void excluiEdital(int idEdital, Usuario usuario) throws PersistenciaException, PrivacidadeException, AutorizacaoException, DadoInconsistenteException {
        
        this.repEdital.excluiEdital(this.repEdital.obtemEdital(idEdital), usuario);
    }
    
    public List<Edital> filtrarEditais(Date data, int idProjeto) throws PersistenciaException {
        
        return this.repEdital.listarEditais(data, this.repProjeto.obter(idProjeto).getTipoProjeto());
    }
    
    public List<Projeto> filtrarProjetosParaInscricao(Set<StatusProjeto> status, Usuario usuario) throws PersistenciaException, AutorizacaoException, DadoInconsistenteException {
        
        return this.repProjeto.filtrarProjetos(usuario, status);
    }
    
    public List<Projeto> listarProjetosSubmetidos(Usuario usuario, Set<StatusProjeto> status) throws PersistenciaException, AutorizacaoException {
        
        return this.repProjeto.listarProjetosSubmetidosHomologacao(usuario, status);
    }
    
    public Arquivo obtemArquivoEdital(int idEdital, int idArquivo, Usuario user) throws PersistenciaException, AutorizacaoException, DadoInconsistenteException {
        
        return this.repEdital.obtemArquivo(idArquivo, idEdital, user);
    }
    
    public void cancelaInscricao(int idEdital, int idProjeto, Usuario user, Date dataCancelamento) throws PersistenciaException, AutorizacaoException, PrivacidadeException, DadoInconsistenteException {
        
        Inscricao inscricao = this.repEdital.obtemInscricao(idProjeto, idEdital);
        this.repEdital.cancelarInscricao(inscricao, user, dataCancelamento);
        inscricao.getProjeto().setStatus(StatusProjeto.HOMOLOGADO);
        this.repProjeto.atualizaStatus(inscricao.getProjeto());
    }
    
    public List<Inscricao> listarInscricoesDoUsuario(Usuario usuario) throws PersistenciaException, AutorizacaoException {
        
        return this.repEdital.listarInscricoes(usuario);
    }
    
    public Arquivo obtemArquivoInscricao(Usuario usuario, int idEdital, int idProjeto) throws PersistenciaException, PrivacidadeException, DadoInconsistenteException, AutorizacaoException {
        
        Inscricao inscricao = this.repEdital.obtemInscricao(idProjeto, idEdital);
        
        if (this.repEdital.verificaConsistenciaDownloadArqInscricao(inscricao, usuario)) {
            
            return inscricao.getArquivo();
        }
        
        return null;
    }
    
    public Projeto obtemProjeto(Usuario usuario, int idProjeto) throws PersistenciaException, AutorizacaoException {
        
        Projeto projeto = this.repProjeto.obter(idProjeto, usuario);
        projeto.setParticipantesAluno(repProjeto.getParticAlunos(idProjeto));
        projeto.setParticipantesProfessor(repProjeto.getParticProfessores(idProjeto));
        projeto.setParticipantesExterno(repProjeto.getParticExternos(idProjeto));
        projeto.setCustos(repProjeto.getCustos(idProjeto));
        projeto.setRecados(repProjeto.listarRecados(idProjeto));
        
        return projeto;
    }
    
    public List<AreaConhecimento> listarAreas() throws PersistenciaException {
        
        return this.repProjeto.listarAreas();
    }
    
    public List<Usuario> listarParticipantes(String papel) throws PersistenciaException {
        
        return this.repUsuario.listar(papel);
    }
    
    public void editaProjetoExec(Projeto projeto, Usuario usuario) throws AutorizacaoException, PrivacidadeException, DadoInconsistenteException, PersistenciaException {
        
        this.repProjeto.editaProjetoEmExecucao(projeto, usuario);
    }
    
    public void contemplaProjeto(int idProjeto, int idEdital, Usuario usuario, Recado recado) throws AutorizacaoException, DadoInconsistenteException, PersistenciaException {
        
        Inscricao inscricao = this.repEdital.obtemInscricao(idProjeto, idEdital);
        inscricao.setProjeto(this.repProjeto.obter(idProjeto));
        this.repEdital.contemplar(inscricao, usuario);
        inscricao.getProjeto().setStatus(StatusProjeto.CONTEMPLADO);
        this.repProjeto.atualizaStatus(inscricao.getProjeto());
        this.repProjeto.addRecado(inscricao.getProjeto(), recado);
    }
    
    public void naoContemplaProjeto(int idProjeto, int idEdital, Usuario usuario, Recado recado) throws AutorizacaoException, DadoInconsistenteException, PersistenciaException {
        
        Inscricao inscricao = this.repEdital.obtemInscricao(idProjeto, idEdital);
        inscricao.setProjeto(this.repProjeto.obter(idProjeto));
        
        if (this.repEdital.naoContemplar(inscricao, usuario)) {
            
            inscricao.getProjeto().setStatus(StatusProjeto.HOMOLOGADO);
            this.repProjeto.atualizaStatus(inscricao.getProjeto());
        }
        
        this.repProjeto.addRecado(inscricao.getProjeto(), recado);
    }
    
    public Inscricao exibeInscricao(int idEdital, int idProjeto, Usuario usuario) throws AutorizacaoException, PrivacidadeException, PersistenciaException {
        
        return this.repEdital.exibeInscricao(idProjeto, idEdital, usuario);
    }
}
