/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

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
public class ControleFilialTest {

    private static List<Integer> idsGerados;
    static int codEmpresa;

    public ControleFilialTest() {
        idsGerados = new ArrayList<>();
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
        for(int i : idsGerados){
            ControleFilial.deleta(i);
        }
    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
        
    }

    /**
     * Test of gravar method, of class ControleFilial.
     */
    @Test
    public void testGravar() {
        System.out.println("gravar");
        int cod = 0;
        String razaoSocial = "teste";
        String nomeFantasia = "teste";
        String ie = "123";
        String numero = "123";
        List<Filial> list = ControleFilial.busca();
        int result = ControleFilial.gravar(cod, razaoSocial, nomeFantasia, ie, numero, codEmpresa);
        idsGerados.add(result);
        assertEquals(list.size()+1, ControleFilial.busca().size());
        assertEquals(razaoSocial, ControleFilial.busca(result).getFilRazaoSocial());
    }

    @Test
    public void testAlterar() {
        System.out.println("Alterar");
        int cod = 0;
        String razaoSocial = "teste";
        String nomeFantasia = "teste";
        String ie = "123";
        String numero = "123";
        Filial fil = ControleFilial.busca(ControleFilial.gravar(cod, razaoSocial, nomeFantasia, ie, numero, codEmpresa));
        numero = "1234";
        String expResult = numero;
        int result = ControleFilial.gravar(fil.getCodFilial(), razaoSocial, nomeFantasia, ie, numero, codEmpresa);
        idsGerados.add(result);
        assertEquals(expResult, ControleFilial.busca(result).getFilNumero());
    }

    /**
     * Test of busca method, of class ControleFilial.
     */
    @Test
    public void testBuscaSemResultado() {
        System.out.println("busca");
        int cod = 0;
        Filial expResult = null;
        Filial result = ControleFilial.busca(cod);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBuscaComResultado() {
        System.out.println("busca");
        int cod = 0;
        String razaoSocial = "teste";
        String nomeFantasia = "teste";
        String ie = "123";
        String numero = "123";
        int resultado = ControleFilial.gravar(cod, razaoSocial, nomeFantasia, ie, numero, codEmpresa);
        idsGerados.add(resultado);
        Integer expResult = resultado;
        Filial result = ControleFilial.busca(resultado);
        assertEquals(expResult, result.getCodFilial());
    }

    /**
     * Test of busca method, of class ControleFilial.
     */
    @Test
    public void testBusca_0args() {
        System.out.println("buscaTodos");
        testGravar();
        List<Filial> result = ControleFilial.busca();
        assertEquals(true, result.size() > 0);
    }

    /**
     * Test of deleta method, of class ControleFilial.
     */
    @Test
    public void testDeletaSemResultado() {
        System.out.println("deleta");
        int cod = 0;
        boolean expResult = false;
        boolean result = ControleFilial.deleta(cod);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDeletaComResultado() {
        System.out.println("deleta");
        int cod = 0;
        String razaoSocial = "teste";
        String nomeFantasia = "teste";
        String ie = "123";
        String numero = "123";
        int resultado = ControleFilial.gravar(cod, razaoSocial, nomeFantasia, ie, numero, codEmpresa);
        idsGerados.add(resultado);
        boolean expResult = true;
        boolean result = ControleFilial.deleta(resultado);
        assertEquals(expResult, result);
    }

}
