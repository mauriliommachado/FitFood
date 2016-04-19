/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import control.ControleEmpresa;
import control.ControleFilial;
import java.util.ArrayList;
import java.util.List;
import model.Filial;
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
public class ServiceFilialTest {

    private static List<Integer> idsGerados;
    Gson gson = new Gson();
    static int codEmpresa;
    static int codFilial;

    public ServiceFilialTest() {
        idsGerados = new ArrayList<>();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        for (int id : idsGerados) {
            ControleFilial.deleta(id);
        }
        ControleEmpresa.deleta(codEmpresa);
        ControleFilial.deleta(codFilial);

    }

    @Before
    public void setUp() {
        codEmpresa = ControleEmpresa.gravar(0, "16383345000101");
        codFilial = ControleFilial.gravar(0, "teste", "teste", "123", "123",true, codEmpresa);

    }

    @After
    public void tearDown() {
        ControleFilial.deleta(codFilial);
    }

    /**
     * Test of getText method, of class ServiceFilial.
     */
    @Test
    public void testGetText() {
        ServiceFilial instance = new ServiceFilial();
        String expResult = "Hello";
        String result = instance.getText();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFiliais method, of class ServiceFilial.
     */
    @Test
    public void testGetFiliaisSemResultado() {
        int id = 0;
        ServiceFilial instance = new ServiceFilial();
        String result = instance.getFiliais(id);
        assertEquals("false", result);
    }

    @Test
    public void testGetFiliaisComResultado() {
        int id = codFilial;
        ServiceFilial instance = new ServiceFilial();
        String result = instance.getFiliais(id);
        assertEquals(ControleFilial.limpaFilial(ControleFilial.busca(id)).toString(), result);
    }

    /**
     * Test of getFiliais method, of class ServiceFilial.
     */
    @Test
    public void testGetFiliais() {
        ServiceFilial instance = new ServiceFilial();
        String expResult = gson.toJson(ControleFilial.limpaFilial(ControleFilial.busca()));
        String result = instance.getFiliais();
        assertEquals(expResult, result);
    }

    /**
     * Test of putFilial method, of class ServiceFilial.
     */
    @Test
    public void testPutFilialVazio() {
        String content = "";
        ServiceFilial instance = new ServiceFilial();
        String expResult = null;
        String result = instance.putFilial(content);
        assertEquals(expResult, result);
    }

    @Test
    public void testPutFilialErrado() {
        String content = "2";
        ServiceFilial instance = new ServiceFilial();
        String expResult = null;
        String result = instance.putFilial(content);
        assertEquals(expResult, result);
    }

    @Test
    public void testPutFilialAltera() {
        String content = "{\"codFilial\":" + codFilial + ",\"filNumero\":\"123\",\"filRazaoSocial\":\"teste\",\"filNomeFantasia\":\"teste\",\"filIE\":\"123\",\"codEmpresa\":{\"codEmpresa\":" + codEmpresa + ",\"empCNPJ\":\"13200345000101\"}}";
        ServiceFilial instance = new ServiceFilial();
        String result = instance.putFilial(content);
        Filial emp = ControleFilial.busca(codFilial);
        String expResult = ControleFilial.limpaFilial(emp).toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testPutFilialGrava() {
        String content = "{\"filNumero\":\"123\",\"filRazaoSocial\":\"teste\",\"filNomeFantasia\":\"teste\",\"filIE\":\"123\",\"codEmpresa\":{\"codEmpresa\":" + codEmpresa + ",\"empCNPJ\":\"13200345000101\"}}";
        ServiceFilial instance = new ServiceFilial();
        String result = instance.putFilial(content);
        Integer expResult = gson.fromJson(result, Filial.class).getCodFilial();
        idsGerados.add(expResult);
        assertEquals(true, expResult > 0);
    }

    /**
     * Test of deleta method, of class ServiceFilial.
     */
    @Test
    public void testDeletaNaoConsegue() {
        int id = 0;
        ServiceFilial instance = new ServiceFilial();
        String expResult = "false";
        String result = instance.deleta(id);
        assertEquals(expResult, result);
    }

    @Test
    public void testDeletaConsegue() {
        int id = codFilial;
        ServiceFilial instance = new ServiceFilial();
        String expResult = "true";
        String result = instance.deleta(id);
        assertEquals(expResult, result);
    }

}
