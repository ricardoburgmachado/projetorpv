/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

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
    
    RepositorioEdital repEdital;
    
    public RepositorioEditalTest() {
        
        this.repEdital = new RepositorioPostgresFactory().createRepositorioEdital();
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
    @Test
    public void testObtem() {
        
        System.out.println(repEdital.obtem(1).getTitulo());
    }
}