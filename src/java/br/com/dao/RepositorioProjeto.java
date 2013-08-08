/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.model.AreaConhecimento;
import br.com.model.Custo;
import br.com.model.Papel;
import br.com.model.Projeto;
import br.com.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class RepositorioProjeto {
    
     private ProjetoDAO projDAO;

    /**
     * Construtor
     * @param projetoDAO 
     */
    public RepositorioProjeto(ProjetoDAO pDAO) {

        this.projDAO = pDAO;
    }
    
    public int inserir(Projeto p){    
        return projDAO.inserir(p);
    }
    
    
    public ArrayList<AreaConhecimento> listarAreas(){  
        //return usuDao.listar(p);
        return projDAO.listarAreas();
    }


    public void inserirCusteios(ArrayList<Custo> c){
        projDAO.inserirCusteios(c);
    }
    
    
    
    
    
    
    
}
