/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import Exceptions.PrivacidadeException;
import br.com.dao.EditalDAO;
import br.com.model.Edital;
import com.sun.faces.util.RequestStateManager;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
            exception = new DadoInconsistenteException(exception, "Prazo inicial não informado!<br/>");
        } 
        
        if (!verificaNulo(edital.getPrazoInicial())&& !verificaNulo(edital.getPrazoFinal())) {
            if (verificaPrazoMenor(edital.getPrazoInicial(), edital.getPrazoFinal())) {
                exception = new DadoInconsistenteException(exception, "Prazo final menor que prazo inicial!<br/>");
            }
        }

        if (verificaNulo(edital.getPrazoFinal())) {
            exception = new DadoInconsistenteException(exception, "Prazo final não informado!<br/>");
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

    public Edital obtemEdital(int idEdital, int idResponsavel) throws PersistenciaException, DadoInconsistenteException, PrivacidadeException {

        if (verificaConsistenciaObterEdital(idEdital, idResponsavel)) {

            Edital edital = this.editalDao.obtem(idEdital, idResponsavel);

            if (verificaNulo(edital)) {

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
    
    
    
    public List<Edital> listarEditais(int idResponsavel)throws PersistenciaException, DadoInconsistenteException {
        return this.editalDao.listarEditais(idResponsavel);
    }
    
}
