/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import control.ControleEmpresa;
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
public class ServiceEmpresaTest {

    static int codEmpresa;
    Gson gson = new Gson();
    private static List<Integer> idsGerados;

    public ServiceEmpresaTest() {
        idsGerados = new ArrayList<>();

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
        for (int i : idsGerados) {
            ControleEmpresa.deleta(i);
        }
    }

    @Before
    public void setUp() {
        codEmpresa = ControleEmpresa.gravar(0, "16383345000101");

    }

    @After
    public void tearDown() {
        ControleEmpresa.deleta(codEmpresa);
    }

    /**
     * Test of getText method, of class ServiceEmpresa.
     */
    @Test
    public void testGetText() {
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
        int id = 0;
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "false";
        String result = instance.getEmpresa(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetEmpresaComRetorno() {
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = gson.toJson(ControleEmpresa.limpaEmpresa(ControleEmpresa.busca(codEmpresa)));
        String result = instance.getEmpresa(codEmpresa);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmpresas method, of class ServiceEmpresa.
     */
    @Test
    public void testGetEmpresas() {
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = gson.toJson(ControleEmpresa.limpaEmpresa(ControleEmpresa.busca()));
        String result = instance.getEmpresas();
        assertEquals(expResult, result);
    }

    /**
     * Test of putEmpresa method, of class ServiceEmpresa.
     */
    @Test
    public void testPutEmpresaVazio() {
        String content = "";
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = null;
        String result = instance.putEmpresa(content);
        assertEquals(expResult, result);
    }

    @Test
    public void testPutEmpresaErrado() {
        String content = "12";
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = null;
        String result = instance.putEmpresa(content);
        assertEquals(expResult, result);
    }

    @Test
    public void testPutEmpresaAltera() {
        String content = "{\"codEmpresa\":" + codEmpresa + ",\"empCNPJ\":\"1283345000109\"}";
        ServiceEmpresa instance = new ServiceEmpresa();
        String result = instance.putEmpresa(content);
        Empresa emp = ControleEmpresa.busca(codEmpresa);
        String expResult = ControleEmpresa.limpaEmpresa(emp).toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testPutEmpresaGrava() {
        String content = "{\"empCNPJ\":\"16383345000123\"}";
        ServiceEmpresa instance = new ServiceEmpresa();
        String result = instance.putEmpresa(content);
        Integer expResult = gson.fromJson(result, Empresa.class).getCodEmpresa();
        idsGerados.add(expResult);
        assertEquals(true, expResult > 0);
    }

    /**
     * Test of deleta method, of class ServiceEmpresa.
     */
    @Test
    public void testDeletaNaoConsegue() {
        int id = 0;
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "false";
        String result = instance.deleta(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testDeletaConsegue() {
        int id = codEmpresa;
        ServiceEmpresa instance = new ServiceEmpresa();
        String expResult = "true";
        String result = instance.deleta(id);
        assertEquals(expResult, result);
    }

}
