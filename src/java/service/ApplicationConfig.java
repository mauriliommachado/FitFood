/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Maurílio
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.Resposta.class);
        resources.add(service.ServiceCategoria.class);
        resources.add(service.ServiceEmpresa.class);
        resources.add(service.ServiceEndereco.class);
        resources.add(service.ServiceFilial.class);
        resources.add(service.ServiceLogin.class);
        resources.add(service.ServiceMarca.class);
        resources.add(service.ServicePedido.class);
        resources.add(service.ServicePessoa.class);
        resources.add(service.ServiceProduto.class);
        resources.add(service.ServiceTelefone.class);
    }
    
}
