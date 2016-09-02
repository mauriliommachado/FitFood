/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import model.Empresa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maurílio
 */
public class ControleEmpresaTest {

    
    private static List<Integer> idsGerados;
    
    public ControleEmpresaTest() {
        ControleEmpresaTest.idsGerados = new ArrayList<>();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        for(int i : idsGerados){
            ControleEmpresa.deleta(i);
        }
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
        
    }

    /**
     * Test of gravar method, of class ControleEmpresa.
     */
    @Test
    public void testGravar() {
        int cod = 0;
        String cnpj = "16383345000101";
        int result = ControleEmpresa.gravar(cod, cnpj);
        idsGerados.add(result);
        List<Empresa> list = ControleEmpresa.busca();
        ControleEmpresa.gravar(cod, cnpj);
        assertEquals(list.size()+1, ControleEmpresa.busca().size());
        assertEquals(cnpj, ControleEmpresa.busca(result).getEmpCNPJ());
    }

    @Test
    public void testAlterar() {
        int cod = 0;
        String cnpj = "16383345000101";
        int result = ControleEmpresa.gravar(cod, cnpj);
        cnpj = "16383345000102";
        result = ControleEmpresa.gravar(result, cnpj);
        idsGerados.add(result);
        String expResult = cnpj;
        assertEquals(expResult, ControleEmpresa.busca(result).getEmpCNPJ());
    }

    /**
     * Test of busca method, of class ControleEmpresa.
     */
    @Test
    public void testBuscaSemResultado() {
        int cod = 0;
        Empresa expResult = null;
        Empresa result = ControleEmpresa.busca(cod);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBuscaComResultado() {
        int cod = 0;
        String cnpj = "16383345000101";
        int id = ControleEmpresa.gravar(cod, cnpj);
        idsGerados.add(id);
        int expResult = id;
        Empresa result = ControleEmpresa.busca(id);
        assertEquals(expResult, (int)result.getCodEmpresa());
    }

    /**
     * Test of busca method, of class ControleEmpresa.
     */
    @Test
    public void testBusca_0args() {
        List<Empresa> result = ControleEmpresa.busca();
        assertSame(result.size() > 0, true);
    }

    /**
     * Test of deleta method, of class ControleEmpresa.
     */
    @Test
    public void testDeleta() {
        int cod = 0;
        String cnpj = "16383345000101";
        int id = ControleEmpresa.gravar(cod, cnpj);
        idsGerados.add(id);
        boolean expResult = true;
        boolean result = ControleEmpresa.deleta(id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDeletaNulo() {
        int cod = 0;
        boolean expResult = false;
        boolean result = ControleEmpresa.deleta(cod);
        assertEquals(expResult, result);
    }

}
