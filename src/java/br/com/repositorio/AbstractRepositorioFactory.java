/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

/**
 *
 * @author rafael
 */
public interface AbstractRepositorioFactory {
    
    RepositorioUsuario createRepositorioUsuario();
    
    RepositorioProjeto createRepositorioProjeto();
    
    RepositorioEdital createRepositorioEdital();
    
}
