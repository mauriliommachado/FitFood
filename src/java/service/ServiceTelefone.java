/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import control.ControleTelefone;
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
import model.Telefone;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServiceTelefone")
public class ServiceTelefone {

    @Context
    private UriInfo context;
    private Gson gson = new Gson();

    public ServiceTelefone() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idTelefone}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTelefone(@PathParam("idTelefone") int id) {
        Telefone telefone = ControleTelefone.busca(id);
        if (telefone != null) {
            ControleTelefone.limpaTelefone(telefone);
        }
        return gson.toJson(telefone != null ? telefone : false);
    }

    @GET
    @Path("busca")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTelefones() {
        List<Telefone> listaTelefone = ControleTelefone.busca();
        return gson.toJson(ControleTelefone.limpaTelefone(listaTelefone));
    }

    @PUT
    @Path("grava")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public String putTelefone(String content) {
        Telefone telefone;
        int cod;
        if (content.isEmpty()) {
            return null;
        }
        try {
            telefone = gson.fromJson(content, Telefone.class);
        } catch (JsonSyntaxException ex) {
            System.out.println(ex);
            return null;
        }
        if (telefone.getCodTelefone() == null) {
            cod = ControleTelefone.gravar(0,telefone.getTelNumero(),telefone.getTelTipo(),telefone.getCodpessoa().getCodPessoa());
        } else {
            cod = ControleTelefone.gravar(telefone.getCodTelefone(), telefone.getTelNumero(),telefone.getTelTipo(),telefone.getCodpessoa().getCodPessoa());
        }
        return gson.toJson(ControleTelefone.limpaTelefone(ControleTelefone.busca(cod)));
    }

    @GET
    @Path("delete/{idTelefone}")
    public String deleta(@PathParam("idTelefone") int id) {
        if (ControleTelefone.deleta(id)) {
            return gson.toJson(true);
        }
        return gson.toJson(false);

    }
}
