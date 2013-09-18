/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import br.com.model.Arquivo;
import br.com.model.Permissao;
import br.com.model.Usuario;
import java.util.Date;
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
public class RepositorioFacadeTest {
    
    private RepositorioFacade facade;
    private RepositorioUsuario repUsuario;
    
    public RepositorioFacadeTest() {
        
        this.facade = new RepositorioFacade();
        this.repUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();
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
     * Test of inscreveEdital method, of class RepositorioFacade.
     */
    @Test
    public void testInscreveEdital() {
        
        Usuario user = this.repUsuario.autenticaUsuario("rafael", "rafael");
        user.addPermissao(Permissao.INSCRICAO_EDITAL);
        byte[] dados = {65, 112, 108, 105, 99, 97, 116, 105, 118, 111, 32, 100, 101, 32, 114, 101, 99, 111, 109, 101, 110, 100, 97, -61, -89, -61, -93, 111, 32, 100, 101, 32, 102, 105, 108, 109, 101, 115, 32, 98, 97, 115, 101, 97, 110, 100, 111, 45, 115, 101, 32, 110, 111, 32, 112, 101, 114, 102, 105, 108, 32, 111, 117, 32, 110, 97, 32, 115, 105, 116, 117, 97, -61, -89, -61, -93, 111, 32, 100, 111, 32, 117, 115, 117, -61, -95, 114, 105, 111, 46};
        Arquivo arquivo = new Arquivo("teste", "txt", dados);
        
        this.facade.inscreveEdital(1, 1, arquivo, new Date(113, 8, 10), user);
    }
}