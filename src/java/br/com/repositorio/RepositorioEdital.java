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
import br.com.model.Arquivo;
import br.com.model.Edital;
import br.com.model.Inscricao;
import br.com.model.Permissao;
import br.com.model.StatusProjeto;
import br.com.model.TipoProjeto;
import br.com.model.Usuario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rafael
 */
public class RepositorioEdital {

    private EditalDAO editalDao;

    public RepositorioEdital(EditalDAO editalDao) {
        this.editalDao = editalDao;
    }

    public void adiciona(Edital edital) throws PersistenciaException, DadoInconsistenteException {

        DadoInconsistenteException exception = null;

        if (edital == null) {

            throw new DadoInconsistenteException("Edital Inválido!<br/>");
        }

        if (verificaVazio(edital.getTitulo())) {

            exception = new DadoInconsistenteException(exception, "Título não informado!<br/>");
        }

        if (verificaNulo(edital.getPrazoInicial())) {
            exception = new DadoInconsistenteException(exception, "Prazo inicial(inscrição) não informado!<br/>");
        }

        if (!verificaNulo(edital.getPrazoInicial()) && !verificaNulo(edital.getPrazoFinal())) {
            if (verificaPrazoMenor(edital.getPrazoInicial(), edital.getPrazoFinal())) {
                exception = new DadoInconsistenteException(exception, "Prazo final(inscrição) menor que prazo inicial!<br/>");
            }
        }
        
        if (verificaNulo(edital.getPrazoFinal())) {
            exception = new DadoInconsistenteException(exception, "Prazo final não(inscrição) informado!<br/>");
        }

        if (verificaNulo(edital.getArquivo())) {
            exception = new DadoInconsistenteException(exception, "Arquivo não anexado!<br/>");
        }
        
        
        if (exception == null) {

            this.editalDao.adiciona(edital);
        } else {

            throw exception;
        }
    }

    public Edital obtemEdital(int idEdital, Usuario user) throws PersistenciaException, DadoInconsistenteException, PrivacidadeException {

        if (verificaConsistenciaObterEdital(idEdital, user)) {

            Edital edital = this.editalDao.obtem(idEdital, user.getId());

            if (edital == null) {

                throw new PrivacidadeException("Edital não pertencente ao usuário!");
            }

            edital.setArquivo(this.editalDao.obtemArquivo(idEdital));
            edital.setRetificacoes(this.editalDao.obterRetificacoes(idEdital));

            return edital;
        }

        return null;
    }

    public Arquivo obtemArquivo(int idArquivo, int idEdital, Usuario user) throws PersistenciaException, AutorizacaoException, DadoInconsistenteException {

        List<Arquivo> arquivos = this.editalDao.obterRetificacoes(idEdital);
        arquivos.add(this.editalDao.obtemArquivo(idEdital));
        
        if(verificaConsistenciaObterArquivo(arquivos, user, idArquivo)){
            
            for(Arquivo arquivo: arquivos){
                
                if(arquivo.getIdArquivo() == idArquivo){
                    
                    return arquivo;
                }
            }
        }
        
        return null;
    }

    private boolean verificaConsistenciaObterArquivo(List<Arquivo> arquivosEdital, Usuario usuario, int idArquivo) throws PersistenciaException, AutorizacaoException, DadoInconsistenteException {

        if (!usuario.getPermissoes().contains(Permissao.CRUD_EDITAL) && !usuario.getPermissoes().contains(Permissao.EXIBE_EDITAL)) {
            
            throw new AutorizacaoException("Usuário sem permissão para obter o arquivo!");
        }
        
        if(!arquivosEdital.contains(new Arquivo(idArquivo, null, null, null))){
            
            throw new DadoInconsistenteException("Arquivo não existente ou não pertencente ao edital!");
        }
        
        return true;
    }

    public Edital obtemEdital(int idEdital) throws PersistenciaException {

        Edital edital = this.editalDao.obtem(idEdital);
        edital.setArquivo(this.editalDao.obtemArquivo(idEdital));
        edital.setRetificacoes(this.editalDao.obterRetificacoes(idEdital));        
        return edital;
    }

    public void excluiEdital(Edital edital, Usuario user) throws PersistenciaException, PrivacidadeException, DadoInconsistenteException {

        if (verificaConsistenciaExcluirEdital(edital, user)) {

            this.editalDao.exclui(edital.getId(), user.getId());
        }
    }

    private boolean verificaConsistenciaObterEdital(int idEdital, Usuario user) throws PersistenciaException, DadoInconsistenteException, AutorizacaoException {

        if (!user.getPermissoes().contains(Permissao.CRUD_EDITAL) && !user.getPermissoes().contains(Permissao.EXIBE_EDITAL)) {

            throw new AutorizacaoException("Usuário sem permissão para excluir edital!");
        }

        if (!exists(idEdital)) {

            throw new DadoInconsistenteException("Edital não existente!");
        }

        return true;
    }

    private boolean verificaConsistenciaExcluirEdital(Edital edital, Usuario usuario) throws PersistenciaException, DadoInconsistenteException, AutorizacaoException, PrivacidadeException {

        if (!usuario.getPermissoes().contains(Permissao.CRUD_EDITAL)) {

            throw new AutorizacaoException("Usuário sem permissão para excluir edital!");
        }

        if (edital == null) {

            throw new DadoInconsistenteException("Edital não existente!");
        }

        if (edital.getProReitor().getId() != usuario.getId()) {

            throw new PrivacidadeException("Edital não pertencente ao usuário!");
        }

        return true;
    }

    private boolean exists(int idEdital) throws PersistenciaException {

        return this.editalDao.exists(idEdital);
    }

    public void inscreveProjetoEdital(Inscricao inscricao, Usuario usuario) throws PersistenciaException, DadoInconsistenteException, PrivacidadeException, AutorizacaoException {

        if (verificaConsistenciaParaInscricao(inscricao, usuario)) {

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

        if (!(inscricao.getDataInscricao().after(inscricao.getEdital().getPrazoInicial()) && inscricao.getDataInscricao().before(inscricao.getEdital().getPrazoFinal()))) {

            exception = new DadoInconsistenteException(exception, "Prazo para inscrição expirado!");
        }

        if (inscricao.getEdital().getTipo() != inscricao.getProjeto().getTipoProjeto()) {

            exception = new DadoInconsistenteException(exception, "Propósitos incompatíveis. Edital para projetos de " + inscricao.getEdital().getTipo() + " e o projeto é de " + inscricao.getProjeto().getTipoProjeto());
        }

        if (this.editalDao.verificaInscricao(inscricao.getEdital().getId(), inscricao.getProjeto().getId())) {

            exception = new DadoInconsistenteException(exception, "Projeto já inscrito no edital!");

        } else if (!inscricao.getProjeto().getStatus().equals(StatusProjeto.HOMOLOGADO) && !inscricao.getProjeto().getStatus().equals(StatusProjeto.INSCRITO)) {

            exception = new DadoInconsistenteException(exception, "Projeto não homologado!");
        }

        if (exception != null) {

            throw exception;
        }

        return true;
    }

    private boolean verificaNulo(Object obj) {

        return obj == null;

    }

    private boolean verificaVazio(String campo) {

        return verificaNulo(campo) || campo.equals("");
    }

    private boolean verificaPrazoMenor(Date ini, Date fim) {

        /*
         Calendar calendar = new GregorianCalendar(); 
         System.out.println("*************DATA ATUAL: "+calendar.getTime().toString());
         System.out.println("*************DATA INCIAL: "+ini);
         System.out.println("*************DATA FINAL: "+fim);
        
        
         if(calendar.getTime().after(ini)) {
         System.out.println("É DEPOIS DA DATA ATUAL");
         }
        
         if(calendar.getTime().before(ini)) {
         System.out.println("É ANTES DA DATA ATUAL");
         }
        
         if( ini.after(fim) &&  calendar.getTime().before(ini)){
         System.out.println("RETURN TRUE NAS DATAS");
         return true;
         }else{
         System.out.println("RETURN FALSE NAS DATAS");
         return false;
         }
         */

        return ini.after(fim);
    }

    public List<Edital> listarEditais(int idResponsavel) throws PersistenciaException, DadoInconsistenteException {

        return this.editalDao.listarEditais(idResponsavel);
    }

    public List<Edital> listarEditais(Date data, TipoProjeto tipo) throws PersistenciaException {

        return this.editalDao.listarEditais(data, tipo);
    }

    public void edita(Edital edital) throws PersistenciaException, DadoInconsistenteException {

        DadoInconsistenteException exception = null;

        if (edital == null) {

            throw new DadoInconsistenteException("Edital Inválido!<br/>");
        }

        if (verificaVazio(edital.getTitulo())) {

            exception = new DadoInconsistenteException(exception, "Título não informado!<br/>");
        }

        if (verificaNulo(edital.getPrazoInicial())) {
            exception = new DadoInconsistenteException(exception, "Prazo inicial não informado!<br/>");
        }

        if (!verificaNulo(edital.getPrazoInicial()) && !verificaNulo(edital.getPrazoFinal())) {
            if (verificaPrazoMenor(edital.getPrazoInicial(), edital.getPrazoFinal())) {
                exception = new DadoInconsistenteException(exception, "Prazo final menor que prazo inicial!<br/>");
            }
        }

        if (verificaNulo(edital.getPrazoFinal())) {
            exception = new DadoInconsistenteException(exception, "Prazo final não informado!<br/>");
        }

        /*
         if (verificaNulo(edital.getArquivo())) {
         exception = new DadoInconsistenteException(exception, "Arquivo não anexado!<br/>");
         }
         */
        if (exception == null) {

            this.editalDao.edita(edital);
        } else {

            throw exception;
        }

    }
    
    public List<Edital> listarEditais() throws PersistenciaException, DadoInconsistenteException {

        return this.editalDao.listarEditais();
    }
      
    
}
