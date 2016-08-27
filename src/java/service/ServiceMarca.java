/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import control.ControleMarca;
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
import model.Marca;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServiceMarca")
public class ServiceMarca {

    @Context
    private UriInfo context;
    private Gson gson = new Gson();

    public ServiceMarca() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idMarca}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMarca(@PathParam("idMarca") int id) {
        Marca marca = ControleMarca.busca(id);
        if (marca != null) {
            ControleMarca.limpaMarca(marca);
        }
        return gson.toJson(marca != null ? marca : false);
    }

    @GET
    @Path("busca")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMarcas() {
        List<Marca> listaMarca = ControleMarca.busca();
        return gson.toJson(ControleMarca.limpaMarca(listaMarca));
    }

    @PUT
    @Path("grava")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public String putMarca(String content) {
        Marca marca;
        int cod;
        if (content.isEmpty()) {
            return null;
        }
        try {
            marca = gson.fromJson(content, Marca.class);
        } catch (JsonSyntaxException ex) {
            System.out.println(ex);
            return null;
        }
        if (marca.getCodMarca() == null) {
            cod = ControleMarca.gravar(0,marca.getMarDescricao(),marca.getCodEmpresa().getCodEmpresa());
        } else {
            cod = ControleMarca.gravar(marca.getCodMarca(), marca.getMarDescricao(),marca.getCodEmpresa().getCodEmpresa());
        }
        return gson.toJson(ControleMarca.limpaMarca(ControleMarca.busca(cod)));
    }

    @GET
    @Path("delete/{idMarca}")
    public String deleta(@PathParam("idMarca") int id) {
        if (ControleMarca.deleta(id)) {
            return gson.toJson(true);
        }
        return gson.toJson(false);

    }
}
