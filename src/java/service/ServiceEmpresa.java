/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import control.ControleEmpresa;
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
import model.Empresa;
import model.Filial;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServiceEmpresa")
public class ServiceEmpresa {

    @Context
    private UriInfo context;
    private Gson gson = new Gson();

    public ServiceEmpresa() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpresa(@PathParam("idEmpresa") int id) {
        Empresa empresa = ControleEmpresa.busca(id);
        if (empresa != null) {
            for (Filial fil : empresa.getFilialList()) {
                fil.setProdutoList(null);
                fil.setCodEmpresa(null);
            }
        }
        return gson.toJson(empresa != null ? empresa : false);
    }

    @GET
    @Path("busca")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpresas() {
        List<Empresa> listaEpresa = ControleEmpresa.busca();
        for (Empresa empresa : listaEpresa) {
            for (Filial fil : empresa.getFilialList()) {
                fil.setCodEmpresa(null);
            }
        }
        return gson.toJson(listaEpresa);
    }

    @PUT
    @Path("grava")
    @Consumes(MediaType.APPLICATION_JSON)
    public String putEmpresa(String content) {
        Empresa empresa = gson.fromJson(content, Empresa.class);
        ControleEmpresa.gravar(empresa.getCodEmpresa(), empresa.getEmpCNPJ());
        return gson.toJson(empresa);
    }

    @GET
    @Path("delete/{idEmpresa}")
    public String deleta(@PathParam("idEmpresa") int id) {
        if (ControleEmpresa.deleta(id)) {
            return gson.toJson(false);
        }
        return gson.toJson(true);

    }
}
