/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
    
    public ServiceFilialTest() {
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
     * Test of getText method, of class ServiceFilial.
     */
    @Test
    public void testGetText() {
        System.out.println("Home Filial");
        ServiceFilial instance = new ServiceFilial();
        String expResult = "Hello";
        String result = instance.getText();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFiliais method, of class ServiceFilial.
     */

    public void testGetFiliais_int() {
        System.out.println("getFiliais");
        int id = 0;
        ServiceFilial instance = new ServiceFilial();
        String expResult = "";
        String result = instance.getFiliais(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFiliais method, of class ServiceFilial.
     */

    public void testGetFiliais_0args() {
        System.out.println("getFiliais");
        ServiceFilial instance = new ServiceFilial();
        String expResult = "";
        String result = instance.getFiliais();
        assertEquals(expResult, result);
    }

    /**
     * Test of putFilial method, of class ServiceFilial.
     */

    public void testPutFilial() {
        System.out.println("putFilial");
        String content = "";
        ServiceFilial instance = new ServiceFilial();
        String expResult = "";
        String result = instance.putFilial(content);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleta method, of class ServiceFilial.
     */

    public void testDeleta() {
        System.out.println("deleta");
        int id = 0;
        ServiceFilial instance = new ServiceFilial();
        String expResult = "";
        String result = instance.deleta(id);
        assertEquals(expResult, result);
    }
    
}
