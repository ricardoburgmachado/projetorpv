/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.PersistenciaException;
import br.com.dao.EditalDAO;
import br.com.model.Edital;

/**
 *
 * @author rafael
 */
public class RepositorioEdital{
    
    private EditalDAO editalDao;

    public RepositorioEdital(EditalDAO editalDao) {
        this.editalDao = editalDao;
    }

    
    public void adiciona(Edital edital) throws PersistenciaException {
        
        this.editalDao.adiciona(edital);
    }
    
}
