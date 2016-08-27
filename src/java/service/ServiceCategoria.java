/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import control.ControleCategoria;
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
import model.Categoria;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServiceCategoria")
public class ServiceCategoria {

    @Context
    private UriInfo context;
    private Gson gson = new Gson();

    public ServiceCategoria() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategoria(@PathParam("idCategoria") int id) {
        Categoria categoria = ControleCategoria.busca(id);
        if (categoria != null) {
            ControleCategoria.limpaCategoria(categoria);
        }
        return gson.toJson(categoria != null ? categoria : false);
    }

    @GET
    @Path("busca")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategorias() {
        List<Categoria> listaCategoria = ControleCategoria.busca();
        return gson.toJson(ControleCategoria.limpaCategoria(listaCategoria));
    }

    @PUT
    @Path("grava")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public String putCategoria(String content) {
        Categoria categoria;
        int cod;
        if (content.isEmpty()) {
            return null;
        }
        try {
            categoria = gson.fromJson(content, Categoria.class);
        } catch (JsonSyntaxException ex) {
            System.out.println(ex);
            return null;
        }
        if (categoria.getCodCategoria() == null) {
            cod = ControleCategoria.gravar(0,categoria.getCatDescricao(),categoria.getCodEmpresa().getCodEmpresa());
        } else {
            cod = ControleCategoria.gravar(categoria.getCodCategoria(), categoria.getCatDescricao(),categoria.getCodEmpresa().getCodEmpresa());
        }
        return gson.toJson(ControleCategoria.limpaCategoria(ControleCategoria.busca(cod)));
    }

    @GET
    @Path("delete/{idCategoria}")
    public String deleta(@PathParam("idCategoria") int id) {
        if (ControleCategoria.deleta(id)) {
            return gson.toJson(true);
        }
        return gson.toJson(false);

    }
}
