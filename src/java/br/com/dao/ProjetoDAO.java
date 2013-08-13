/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Aluno;
import br.com.model.AreaConhecimento;
import br.com.model.Custo;
import br.com.model.Externo;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.TipoProjeto;
import br.com.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface ProjetoDAO {
    
     public int inserir(Projeto p) throws PersistenciaException;
     public Projeto obter(Projeto p) throws PersistenciaException;
     public Projeto obter(int id) throws PersistenciaException;
     public void editar(Projeto p) throws PersistenciaException;
     public void excluir(Projeto p) throws PersistenciaException;  
     public ArrayList<AreaConhecimento> listarAreas() throws PersistenciaException;  
     //public ArrayList<TipoProjeto> listarTiposProjeto() throws PersistenciaException;  
     
     public ArrayList<Aluno> getParticAlunos(int id) throws PersistenciaException;
     public ArrayList<Professor> getParticProfessores(int id) throws PersistenciaException;
     public ArrayList<Externo> getParticExternos(int id) throws PersistenciaException;
     
     public ArrayList<Custo> getCustos(int idProj) throws PersistenciaException;
     
     public void atualizaCustos(int idProj, ArrayList<Custo> c) throws PersistenciaException;
     
     public void atualizaParticipantes(int idProj, ArrayList<String> u) throws PersistenciaException;
     
     public AreaConhecimento getArea(int idProj)throws PersistenciaException;
     
     public void inserirCustos(ArrayList<Custo> c) throws PersistenciaException;
     
     public void insereArquivo(int idProj) throws PersistenciaException;
     
     public void removeArquivo(int idProj) throws PersistenciaException;
     
     public void inserirParticipantes(int idProj, String[] idPart) throws PersistenciaException;
     
     public void inserirParticipantes(int idProj, ArrayList<String> list) throws PersistenciaException;
     
}
