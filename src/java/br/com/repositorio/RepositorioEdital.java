/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import br.com.dao.EditalDAO;
import br.com.model.Edital;

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

    public Edital obtem(int idEdital, int idResponsavel) throws PersistenciaException, DadoInconsistenteException {

        if (verificaConsistenciaObterEdital(idEdital, idResponsavel)) {

            Edital edital = this.editalDao.obtem(idEdital);
            edital.setArquivo(this.editalDao.obtemArquivo(idEdital));
            return edital;
        }
        
        return null;
    }

    private boolean verificaConsistenciaObterEdital(int idEdital, int idResponsavel) throws DadoInconsistenteException {

        if (!exists(idEdital)) {

            throw new DadoInconsistenteException("Edital não existente!");
        }

        return true;
    }

    private boolean exists(int idEdital) throws PersistenciaException {

        return this.editalDao.exists(idEdital);
    }
}
