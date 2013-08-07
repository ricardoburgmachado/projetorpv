/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.us17dao;

import br.com.us17model.ItemProjeto;
import java.util.List;

/**
 *
 * @author Wolmir
 */
public interface ItemProjetoDAO {
    
    public List<ItemProjeto> getNHomologados();
    
    public void homologar(ItemProjeto item_projeto);
}
