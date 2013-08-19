/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositorio;

import br.com.model.Campus;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.StatusProjeto;
import java.util.List;
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
public class RepositorioProjetoTest {

    private RepositorioProjeto repProjeto;

    public RepositorioProjetoTest() {

        this.repProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
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

//    
//    /**
//     * Test of inserir method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testInserir() {
//        System.out.println("inserir");
//        Projeto p = null;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        int expResult = 0;
//        int result = instance.inserir(p);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of listarAreas method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testListarAreas() {
//        System.out.println("listarAreas");
//        RepositorioProjeto instance = new RepositorioProjeto();
//        ArrayList expResult = null;
//        ArrayList result = instance.listarAreas();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of inserirCustos method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testInserirCustos() {
//        System.out.println("inserirCustos");
//        ArrayList<Custo> c = null;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        instance.inserirCustos(c);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of inserirParticipantes method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testInserirParticipantes() {
//        System.out.println("inserirParticipantes");
//        int idProj = 0;
//        String[] idPart = null;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        instance.inserirParticipantes(idProj, idPart);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of valoresValido method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testValoresValido() {
//        System.out.println("valoresValido");
//        String[] idPart = null;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        boolean expResult = false;
//        boolean result = instance.valoresValido(idPart);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obter method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testObter_int() {
//        System.out.println("obter");
//        int id = 0;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        Projeto expResult = null;
//        Projeto result = instance.obter(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obter method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testObter_Projeto() {
//        System.out.println("obter");
//        Projeto p = null;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        Projeto expResult = null;
//        Projeto result = instance.obter(p);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getParticAlunos method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testGetParticAlunos() {
//        System.out.println("getParticAlunos");
//        int id = 0;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        ArrayList expResult = null;
//        ArrayList result = instance.getParticAlunos(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getParticProfessores method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testGetParticProfessores() {
//        System.out.println("getParticProfessores");
//        int id = 0;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        ArrayList expResult = null;
//        ArrayList result = instance.getParticProfessores(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getParticExternos method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testGetParticExternos() {
//        System.out.println("getParticExternos");
//        int id = 0;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        ArrayList expResult = null;
//        ArrayList result = instance.getParticExternos(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCustos method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testGetCustos() {
//        System.out.println("getCustos");
//        int idProj = 0;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        ArrayList expResult = null;
//        ArrayList result = instance.getCustos(idProj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of insereArquivo method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testInsereArquivo() {
//        System.out.println("insereArquivo");
//        int idProj = 0;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        instance.insereArquivo(idProj);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeArquivo method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testRemoveArquivo() {
//        System.out.println("removeArquivo");
//        int idProj = 0;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        instance.removeArquivo(idProj);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editar method, of class RepositorioProjeto.
//     */
//    @Test
//    public void testEditar() {
//        System.out.println("editar");
//        Projeto p = null;
//        RepositorioProjeto instance = new RepositorioProjeto();
//        instance.editar(p);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of listarProjetos method, of class RepositorioProjeto.
     */
    @Test
    public void testListarProjetos() {
        System.out.println("listarProjetos");
        int idResponsavel = 5;
        List<Projeto> result = this.repProjeto.listarProjetos(idResponsavel);
        assertEquals(false, result.isEmpty());

        for (Projeto projeto : result) {

            System.out.println(projeto);
        }
    }

    //@Test
    public void testSubmissaoHomologacao() {
        
        System.out.println("Submissão de projeto para homologação");
        Projeto projeto = this.repProjeto.obter(1);
        projeto.setProfessor(new Professor("Professor", Campus.ALEGRETE));
        this.repProjeto.submeterHomologacao(projeto);
        projeto =  this.repProjeto.obter(1);
        
        assertEquals(projeto.getStatus(), StatusProjeto.SUBMETIDO_HOMOLOGACAO);
    }
    
    @Test
    public void testSubmissaoHomologacaoById(){
        
        System.out.println("Submissão de projeto para homologação por id");
        
        this.repProjeto.submeterHomologacao(1);
        Projeto projeto =  this.repProjeto.obter(1);
        
        assertEquals(projeto.getStatus(), StatusProjeto.SUBMETIDO_HOMOLOGACAO);
    }
}