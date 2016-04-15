/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.ControleEmpresa;
import control.ControleFilial;
import control.GraphAdapterBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Filial;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServiceFilial")
public class ServiceFilial {

    @Context
    private UriInfo context;
    private Gson gson = new Gson();

    public ServiceFilial() {
    }

    @GET
    @Produces("application/json")
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idFilial}")
    @Produces("application/json")
    public String getFiliais(@PathParam("idFilial") int id) {
        Filial filial = ControleFilial.busca(id);
        return gson.toJson(filial != null ? gson.toJson(filial) : gson.toJson(false));
    }

    @GET
    @Path("busca")
    @Produces("application/json")
    public String getFiliais() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        new GraphAdapterBuilder()
                .addType(Filial.class)
                .registerOn(gsonBuilder);
        gson=gsonBuilder.create();
        return gson.toJson(ControleFilial.busca());
    }

    @PUT
    @Path("grava")
    @Consumes(MediaType.TEXT_PLAIN)
    public String putFilial(String content) {
        
        Filial filial = gson.fromJson(content, Filial.class);
        ControleFilial.gravar(filial.getCodFilial(), filial.getFilRazaoSocial(), filial.getFilNomeFantasia(), filial.getFilIE(), filial.getFilNumero(), filial.getCodEmpresa().getCodEmpresa());
        return gson.toJson(filial);
    }

    @GET
    @Path("delete/{idFilial}")
    public String deleta(@PathParam("idFilial") int id) {
        if (ControleEmpresa.deleta(id)) {
            return gson.toJson(false);
        }
        return gson.toJson(true);

    }
}
