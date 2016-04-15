/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import control.ControleEmpresa;
import control.ControleFilial;
import java.util.ArrayList;
import java.util.List;
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
public class ServiceEmpresaTest {

    static int codEmpresa;
    
    public ServiceEmpresaTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        if (ControleEmpresa.busca().isEmpty()) {
            ControleEmpresa.gravar(0, "16383345000101");
        }
        codEmpresa= ControleEmpresa.busca().get(ControleEmpresa.busca().size()-1).getCodEmpresa();
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
     * Test of getText method, of class ServiceEmpresa.
     */
    @Test
    public void testGetText() {
        System.out.println("Home Empresa");
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "Hello";
        String result = instance.getText();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmpresa method, of class ServiceEmpresa.
     */
    @Test
    public void testGetEmpresaSemRetorno() {
        System.out.println("getEmpresa");
        int id = 0;
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "false";
        String result = instance.getEmpresa(id);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetEmpresaComRetorno() {
        System.out.println("getEmpresa Retorno");
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "false";
        String result = instance.getEmpresa(codEmpresa);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmpresas method, of class ServiceEmpresa.
     */
    public void testGetEmpresas() {
        System.out.println("getEmpresas");
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "";
        String result = instance.getEmpresas();
        assertEquals(expResult, result);
    }

    /**
     * Test of putEmpresa method, of class ServiceEmpresa.
     */
    public void testPutEmpresa() {
        System.out.println("putEmpresa");
        String content = "";
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "";
        String result = instance.putEmpresa(content);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleta method, of class ServiceEmpresa.
     */
    public void testDeleta() {
        System.out.println("deleta");
        int id = 0;
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "";
        String result = instance.deleta(id);
        assertEquals(expResult, result);
    }

}
