/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import Exceptions.PersistenciaException;
import br.com.model.Aluno;
import br.com.model.AreaConhecimento;
import br.com.model.Campus;
import br.com.model.Custo;
import br.com.model.Externo;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.Recado;
import br.com.model.StatusProjeto;
import br.com.model.TipoProjeto;
import br.com.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public void excluir(int idProj) throws PersistenciaException;

    public ArrayList<AreaConhecimento> listarAreas() throws PersistenciaException;

    public ArrayList<Aluno> getParticAlunos(int id) throws PersistenciaException;

    public ArrayList<Professor> getParticProfessores(int id) throws PersistenciaException;

    public ArrayList<Externo> getParticExternos(int id) throws PersistenciaException;

    public ArrayList<Custo> getCustos(int idProj) throws PersistenciaException;

    public void atualizaCustos(int idProj, ArrayList<Custo> c) throws PersistenciaException;

    public void atualizaParticipantes(int idProj, ArrayList<String> u) throws PersistenciaException;

    public AreaConhecimento getArea(int idProj) throws PersistenciaException;

    public void inserirCustos(ArrayList<Custo> c) throws PersistenciaException;

    public void insereArquivo(int idProj) throws PersistenciaException;

    public void removeArquivo(int idProj) throws PersistenciaException;

    public void adicionaParticipantes(int idProjeto, Set<Usuario> participantes) throws PersistenciaException;

    public void removeParticipantes(int idProjeto, Set<Usuario> participantes) throws PersistenciaException;

    public void inserirParticipantes(int idProj, String[] idPart) throws PersistenciaException;

    public void inserirParticipantes(int idProj, ArrayList<String> list) throws PersistenciaException;

    public List<Projeto> listarProjetos(int idResponsavel) throws PersistenciaException;

    public List<Projeto> listarProjetos(int idResponsavel, Set<StatusProjeto> status) throws PersistenciaException;

    public List<Usuario> carregaParticipantes(int idProjeto) throws PersistenciaException;

    public void carregaCustos(Projeto projeto) throws PersistenciaException;

    public void atualizaStatus(Projeto projeto) throws PersistenciaException;

    public String consultaStatus(int idProj) throws PersistenciaException;

    public List<Projeto> listarProjetos(int idResponsavel, Set<StatusProjeto> status, Campus campus, TipoProjeto tipo) throws PersistenciaException;

    void addRecado(int idProjeto, Recado recado) throws PersistenciaException;

    List<Recado> listarRecados(int idProjeto) throws PersistenciaException;

    public void alteraStatus(Projeto projeto) throws PersistenciaException;

    public void prestaContas(Projeto projeto) throws PersistenciaException;

    public List<Projeto> listarProjetosInscritos(String tipoProjeto) throws PersistenciaException;

    public List<Projeto> listarProjetos() throws PersistenciaException;
}
