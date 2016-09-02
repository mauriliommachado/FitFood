/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import control.ControleFilial;
import java.util.List;
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
        if (filial != null) {
            filial = ControleFilial.limpaFilial(filial);
        }
        return gson.toJson(filial != null ? filial : false);
    }

    @GET
    @Path("buscaPorEmpresa/{idEmpresa}")
    @Produces("application/json")
    public String getFiliaisPorEmpresa(@PathParam("idEmpresa") int id) {
        List<Filial> filial = ControleFilial.buscaPorEmpresa(id);
        if (filial != null) {
            filial = ControleFilial.limpaFilial(filial);
        }
        return gson.toJson(filial != null ? filial : null);
    }

    @GET
    @Path("busca")
    @Produces("application/json")
    public String getFiliais() {
        List<Filial> lista = ControleFilial.busca();
        return gson.toJson(ControleFilial.limpaFilial(lista));
    }

    @PUT
    @Path("grava")
    @Consumes(MediaType.TEXT_PLAIN)
    public String putFilial(String content) {
        Filial filial;
        int cod;
        if (content.isEmpty()) {
            return null;
        }
        try {
            filial = gson.fromJson(content, Filial.class);
        } catch (JsonSyntaxException ex) {
            System.out.println(ex);
            return null;
        }
        if (filial.getCodFilial() == null || filial.getCodFilial() == 0) {
            cod = ControleFilial.gravar(0, filial.getFilRazaoSocial(), filial.getFilNomeFantasia(), filial.getFilIE(), filial.getFilNumero(), filial.getFilAtiva(), filial.getFilCNPJ(),filial.getCodEmpresa().getCodEmpresa());
        } else {
            cod = ControleFilial.gravar(filial.getCodFilial(), filial.getFilRazaoSocial(), filial.getFilNomeFantasia(), filial.getFilIE(), filial.getFilNumero(), filial.getFilAtiva(), filial.getFilCNPJ(),filial.getCodEmpresa().getCodEmpresa());
        }
        return gson.toJson(ControleFilial.limpaFilial(ControleFilial.busca(cod)));
    }

    @GET
    @Path("delete/{idFilial}")
    public String deleta(@PathParam("idFilial") int id) {
        if (ControleFilial.deleta(id)) {
            return gson.toJson(true);
        }
        return gson.toJson(false);
    }
}
