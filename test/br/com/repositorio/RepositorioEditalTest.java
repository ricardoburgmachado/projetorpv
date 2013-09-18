/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.AutorizacaoException;
import Exceptions.DadoInconsistenteException;
import Exceptions.PrivacidadeException;
import br.com.model.Arquivo;
import br.com.model.Edital;
import br.com.model.Inscricao;
import br.com.model.Projeto;
import br.com.model.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafael
 */
public class RepositorioEditalTest {

    RepositorioFacade facade;

    public RepositorioEditalTest() {

        this.facade = new RepositorioFacade();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of adiciona method, of class RepositorioEdital.
     */
    //@Test
    public void testAdiciona() {
    }

    /**
     * Test of obtem method, of class RepositorioEdital.
     */
    //@Test(expected = DadoInconsistenteException.class)
    public void testObtemEditalInexistente() {

        //repEdital.obtemEdital(3, 1);
    }
    
    //@Test(expected = PrivacidadeException.class)
    public void testObtemEditalNaoPertencenteUsuario(){
        
        //repEdital.obtemEdital(1, 2);
    }
    
   // @Test
    public void testObtemSucesso(){
        
        //repEdital.obtemEdital(1, 1);
    }
    
    //@Test(expected = DadoInconsistenteException.class)
    public void testExcluiEditalInexistente(){
        
        Usuario user = this.facade.autenticaUsuario("joao", "joao");
        this.facade.excluiEdital(3, user);
    }
    
    //@Test(expected = PrivacidadeException.class)
    public void testExcluiEditalNaoPertencenteUsuario(){
        
        Usuario user = this.facade.autenticaUsuario("rafael", "rafael");
        this.facade.excluiEdital(1, user);
    }
    
    @Test (expected = AutorizacaoException.class)
    public void testExcluiSemPermissao(){
        
        Usuario user = this.facade.autenticaUsuario("rafael", "rafael");
        //System.out.println(user.getPermissoes());
        this.facade.excluiEdital(1, user);    
    }
    
    //@Test
    public void testExcluirSucesso(){
        
        //assertEquals(true, repEdital.excluiEdital(1, 1));
    }
}