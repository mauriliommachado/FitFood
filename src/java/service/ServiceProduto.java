/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import control.ControleProduto;
import java.util.Date;
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
import model.Produto;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServiceProduto")
public class ServiceProduto {

    @Context
    private UriInfo context;
    private final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();

    public ServiceProduto() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idProduto}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPessoa(@PathParam("idProduto") int id) {
        Produto produto = ControleProduto.busca(id);
        if (produto != null) {
            ControleProduto.limpaProduto(produto);
        }
        return gson.toJson(produto != null ? produto : false);
    }

    @GET
    @Path("buscaPorFilial/{idFilial}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPessoasPorFilial(@PathParam("idFilial")int codFilial) {
        List<Produto> listaProduto = ControleProduto.buscaPorFilial(codFilial);
        return gson.toJson(listaProduto != null ? ControleProduto.limpaProduto(listaProduto) : false);
    }
    

    @PUT
    @Path("grava")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public String putProduto(String content) {
        Produto produto;
        int cod;
        if (content.isEmpty()) {
            return null;
        }
        try {
            produto = gson.fromJson(content, Produto.class);
        } catch (JsonSyntaxException ex) {
            System.out.println(ex);
            return null;
        }
        if (produto.getCodProduto()==0) {
            cod = ControleProduto.gravar(0,produto.getCodCategoria().getCodCategoria(),produto.getCodFilial().getCodFilial(),produto.getCodMarca().getCodMarca(),produto.getProDescricao(),produto.getProReferencia(),produto.getProUrlImagem(),produto.getProPrecoVenda());
        } else {
            cod = ControleProduto.gravar(produto.getCodProduto(),produto.getCodCategoria().getCodCategoria(),produto.getCodFilial().getCodFilial(),produto.getCodMarca().getCodMarca(),produto.getProDescricao(),produto.getProReferencia(),produto.getProUrlImagem(),produto.getProPrecoVenda());
        }
        return gson.toJson(ControleProduto.limpaProduto(ControleProduto.busca(cod)));
    }

    @GET
    @Path("delete/{idProduto}")
    public String deleta(@PathParam("idProduto") int id) {
        if (ControleProduto.deleta(id)) {
            return gson.toJson(true);
        }
        return gson.toJson(false);
    }
    
}
