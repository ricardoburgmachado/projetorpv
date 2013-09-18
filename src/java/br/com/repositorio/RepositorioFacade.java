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
import br.com.model.Usuario;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class RepositorioFacade {
    
    private RepositorioUsuario repUsuario;
    private RepositorioProjeto repProjeto;
    private RepositorioEdital repEdital;
    
    public RepositorioFacade(){
        
        RepositorioPostgresFactory repositorioFactory = new RepositorioPostgresFactory();
        this.repUsuario = repositorioFactory.createRepositorioUsuario();
        this.repProjeto = repositorioFactory.createRepositorioProjeto();
        this.repEdital = repositorioFactory.createRepositorioEdital();
    }
    
    public void inscreveEdital(int idEdital, int idProjeto, Arquivo arquivo, Date dataInscricao, Usuario usuario ){
        
        Edital edital = repEdital.obtemEdital(idEdital, usuario.getId());
        Projeto projeto = repProjeto.obter(idProjeto);
        Inscricao inscricao = edital.inscreveProjeto(projeto, arquivo, dataInscricao);
        
        this.repEdital.inscreveProjetoEdital(inscricao, usuario);
    }
    
     public Usuario autenticaUsuario(String login, String senha) throws PersistenciaException{
        
        return this.repUsuario.autenticaUsuario(login, senha);
    }

    public void excluiEdital(int idEdital, Usuario usuario) throws PersistenciaException, PrivacidadeException, AutorizacaoException, DadoInconsistenteException{
        
        this.repEdital.excluiEdital(this.repEdital.obtemEdital(idEdital), usuario);
    }
    
}
