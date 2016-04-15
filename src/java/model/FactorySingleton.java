/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author MrPerez
 */
public class FactorySingleton {

    private EntityManagerFactory emf;
    private static FactorySingleton instanceFactory;
    protected static String host = "localhost";
    protected static String porta = "3306";
    protected static String banco = "fitapp";
    protected static String user = "root";
    protected static String pwd = "admin";

    private FactorySingleton() {
        try {
            Map properties = new HashMap();
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("javax.persistence.jdbc.url", "jdbc:mysql://" + host + ":" + porta + "/" + banco);
            properties.put("javax.persistence.jdbc.user", user);
            properties.put("javax.persistence.jdbc.password", pwd);
            try {
                emf = Persistence.createEntityManagerFactory("FitFoodPU", properties);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco!\n\n" + ex.getMessage());
        }
    }

    public static FactorySingleton getInstanceFactory() {
        if (instanceFactory == null) {
            return new FactorySingleton();
        }
        return instanceFactory;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        emf.close();
        //emf=null;
    }

}
