/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.AutorizacaoException;
import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import Exceptions.PrivacidadeException;
import br.com.dao.EditalDAO;
import br.com.model.Edital;
import br.com.model.Inscricao;
import br.com.model.Permissao;
import br.com.model.StatusProjeto;
import br.com.model.Usuario;

/**
 *
 * @author rafael
 */
public class RepositorioEdital {

    private EditalDAO editalDao;

    public RepositorioEdital(EditalDAO editalDao) {
        this.editalDao = editalDao;
    }

    public void adiciona(Edital edital) throws PersistenciaException {

        this.editalDao.adiciona(edital);
    }

    public Edital obtemEdital(int idEdital, int idResponsavel) throws PersistenciaException, DadoInconsistenteException, PrivacidadeException {

        if (verificaConsistenciaObterEdital(idEdital, idResponsavel)) {

            Edital edital = this.editalDao.obtem(idEdital, idResponsavel);

            if (edital == null) {

                throw new PrivacidadeException("Edital não pertencente ao usuário!");
            }

            edital.setArquivo(this.editalDao.obtemArquivo(idEdital));
            return edital;
        }

        return null;
    }

    public boolean excluiEdital(int idEdital, int idResponsavel) throws PersistenciaException, DadoInconsistenteException, PrivacidadeException {

        if (verificaConsistenciaExcluirEdital(idEdital, idResponsavel)) {

            if (!this.editalDao.exclui(idEdital, idResponsavel)) {

                throw new PrivacidadeException("Edital não pertencente ao usuário!");
            }
        }

        return true;
    }

    private boolean verificaConsistenciaObterEdital(int idEdital, int idResponsavel) throws PersistenciaException, DadoInconsistenteException {

        if (!exists(idEdital)) {

            throw new DadoInconsistenteException("Edital não existente!");
        }

        return true;
    }

    private boolean verificaConsistenciaExcluirEdital(int idEdital, int idResponsavel) throws PersistenciaException, DadoInconsistenteException {

        if (!exists(idEdital)) {

            throw new DadoInconsistenteException("Edital não existente!");
        }

        return true;
    }

    private boolean exists(int idEdital) throws PersistenciaException {

        return this.editalDao.exists(idEdital);
    }

    public void inscreveProjetoEdital(Inscricao inscricao, Usuario usuario) throws PersistenciaException, DadoInconsistenteException, PrivacidadeException, AutorizacaoException {
        
        if(verificaConsistenciaParaInscricao(inscricao, usuario)){
            
            inscricao.getProjeto().setStatus(StatusProjeto.INSCRITO);
            this.editalDao.inscreveProjetoEdital(inscricao);
        }
    }

    private boolean verificaConsistenciaParaInscricao(Inscricao inscricao, Usuario usuario) throws PersistenciaException, DadoInconsistenteException, PrivacidadeException, AutorizacaoException {

        DadoInconsistenteException exception = null;

        if (!usuario.getPermissoes().contains(Permissao.INSCRICAO_EDITAL)) {

            throw new AutorizacaoException("Usuario sem permissão para inscrever projeto!");
        }

        if (inscricao == null) {

            throw new DadoInconsistenteException("Dados de inscrição não informados!");
        }

        if (inscricao.getEdital() == null) {

            exception = new DadoInconsistenteException("Edital não existente!");
        }

        if (inscricao.getProjeto() == null) {

            exception = new DadoInconsistenteException(exception, "Projeto não existente!");
        }

        if (!inscricao.getProjeto().getProfessor().equals(usuario)) {

            exception = new PrivacidadeException(exception, "Projeto não pertencente ao usuário!");
        }

        if (!(inscricao.getDataInscricao().after(inscricao.getEdital().getPrazoInicial()) && inscricao.getDataInscricao().before(inscricao.getEdital().getPrazoInicial()))) {
            
            exception = new DadoInconsistenteException(exception, "Prazo para inscrição expirado!");
        }
        
        if (inscricao.getEdital().getTipo() != inscricao.getProjeto().getTipoProjeto()){
            
            exception = new DadoInconsistenteException(exception, "Propósitos incompatíveis. Edital para projetos de " + inscricao.getEdital().getTipo() + " e o projeto é de " + inscricao.getProjeto().getTipoProjeto());
        }
        
        if (this.editalDao.verificaInscricao(inscricao.getEdital().getId(), inscricao.getProjeto().getId())){
            
            exception = new DadoInconsistenteException(exception, "Projeto já inscrito no edital!");
        }else if (!inscricao.getProjeto().getStatus().equals(StatusProjeto.HOMOLOGADO) || !inscricao.getProjeto().getStatus().equals(StatusProjeto.INSCRITO)){
            
            exception = new DadoInconsistenteException(exception, "Projeto não homologado!");
        }

        if(exception != null){
            
            throw exception;
        }
        
        return true;
    }
}
