/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.JsonObject;
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
public class ServiceLoginTest {
    
    public ServiceLoginTest() {
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
     * Test of getText method, of class ServiceLogin.
     */
    @Test
    public void testGetText() {
        ServiceLogin instance = new ServiceLogin();
        String expResult = "Hello";
        String result = instance.getText();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class ServiceLogin.
     */
    @Test
    public void testGetStatus() {
        ServiceLogin instance = new ServiceLogin();
        JsonObject json = new JsonObject();
        json.addProperty("status", 1);
        String expResult = json.toString();
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }
    
    
}
