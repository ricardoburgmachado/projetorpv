package br.com.dao;

import Exceptions.PersistenciaException;

import br.com.model.Permissao;

import br.com.model.Projeto;
import java.util.ArrayList;
import br.com.model.Usuario;

public interface UsuarioDAO {

    public ArrayList listar(String papel) throws PersistenciaException;

    public void obter(Projeto p) throws PersistenciaException;
    
    public Usuario autentica(String login, String senha) throws PersistenciaException;

}
