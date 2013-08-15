/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

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
public class RepositorioUsuarioTest {
    
    public RepositorioUsuarioTest() {
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
     * Test of autenticaUsuario method, of class RepositorioUsuario.
     */
    @Test
    public void testAutenticaUsuario() {
        System.out.println("autenticaUsuario");
        String login = "rafael";
        String senha = "rafael";
        RepositorioUsuario instance = new RepositorioPostgresFactory().createRepositorioUsuario();
        Usuario expResult = null;
        Usuario result = instance.autenticaUsuario(login, senha);
        System.out.println(result);
    }
}