/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import Exceptions.AutorizacaoException;
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

/**
 *
 * @author rafael
 */
public class RepositorioEditalTest {

    RepositorioEdital repEdital;
    RepositorioFacade facade;

    public RepositorioEditalTest() {

        this.repEdital = new RepositorioPostgresFactory().createRepositorioEdital();
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
    public void testObtemEditalNaoPertencenteUsuario() {
        //repEdital.obtemEdital(1, 2);
    }

    // @Test
    public void testObtemSucesso() {

        Usuario user = this.facade.autenticaUsuario("joao", "joao");
        this.facade.exibeEdital(1, user);
    }

    //@Test(expected = DadoInconsistenteException.class)
    public void testExcluiEditalInexistente() {

        Usuario user = this.facade.autenticaUsuario("joao", "joao");
        this.facade.excluiEdital(3, user);
    }

    //@Test(expected = PrivacidadeException.class)
    public void testExcluiEditalNaoPertencenteUsuario() {

        Usuario user = this.facade.autenticaUsuario("rafael", "rafael");
        this.facade.excluiEdital(1, user);
    }

    @Test(expected = AutorizacaoException.class)
    public void testExcluiSemPermissao() {

        Usuario user = this.facade.autenticaUsuario("rafael", "rafael");
        //System.out.println(user.getPermissoes());
        this.facade.excluiEdital(1, user);
    }

    //@Test
    public void testExcluirSucesso() {

        Projeto proj = new Projeto();
        proj.setId(1);
        Edital edital = new Edital();
        edital.setId(3);
        byte[] dados = {65, 112, 108, 105, 99, 97, 116, 105, 118, 111, 32, 100, 101, 32, 114, 101, 99, 111, 109, 101, 110, 100, 97, -61, -89, -61, -93, 111, 32, 100, 101, 32, 102, 105, 108, 109, 101, 115, 32, 98, 97, 115, 101, 97, 110, 100, 111, 45, 115, 101, 32, 110, 111, 32, 112, 101, 114, 102, 105, 108, 32, 111, 117, 32, 110, 97, 32, 115, 105, 116, 117, 97, -61, -89, -61, -93, 111, 32, 100, 111, 32, 117, 115, 117, -61, -95, 114, 105, 111, 46};
        Inscricao inscricao = new Inscricao(proj, edital, new Arquivo("arquivo", "txt", dados));
        this.repEdital.inscreveProjetoEdital(inscricao, null);
        //repEdital.obtemEdital(1, 1);
    }
}