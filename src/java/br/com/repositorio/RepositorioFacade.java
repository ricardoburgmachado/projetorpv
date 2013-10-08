/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.AutorizacaoException;
import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import Exceptions.PrivacidadeException;
import br.com.model.Arquivo;
import br.com.model.Edital;
import br.com.model.Inscricao;
import br.com.model.Projeto;
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
    
    public List<Projeto> filtrarProjetosParaInscricao(Set<StatusProjeto> status, Usuario usuario) throws PersistenciaException, AutorizacaoException, DadoInconsistenteException{
        
        return this.repProjeto.filtrarProjetos(usuario, status);
    }
    
    public List<Projeto> listarProjetosSubmetidos(Usuario usuario, Set<StatusProjeto> status) throws PersistenciaException, AutorizacaoException{
        
        return this.repProjeto.listarProjetosSubmetidosHomologacao(usuario, status);
    }
    
    public Arquivo obtemArquivoEdital(int idEdital, int idArquivo, Usuario user) throws PersistenciaException, AutorizacaoException, DadoInconsistenteException{
        
        return this.repEdital.obtemArquivo(idArquivo, idEdital, user);
    }
    
    public void cancelaInscricao(int idEdital, int idProjeto, Usuario user, Date dataCancelamento) throws PersistenciaException, AutorizacaoException, PrivacidadeException, DadoInconsistenteException{
        
        Inscricao inscricao = this.repEdital.obtemInscricao(idProjeto, idEdital);
        this.repEdital.cancelarInscricao(inscricao, user, dataCancelamento);
        inscricao.getProjeto().setStatus(StatusProjeto.HOMOLOGADO);
        this.repProjeto.atualizaStatus(inscricao.getProjeto());
    }  
    
    public List<Inscricao> listarInscricoesDoUsuario(Usuario usuario) throws PersistenciaException, AutorizacaoException{
        
        return this.repEdital.listarInscricoes(usuario);
    }
}
