/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import control.ControlePedido;
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
import model.Pedido;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServicePedido")
public class ServicePedido {

    @Context
    private UriInfo context;
    private Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create(); ;

    public ServicePedido() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idPedido}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPedido(@PathParam("idPedido") int id) {
        Pedido pedido = ControlePedido.busca(id);
        if (pedido != null) {
            ControlePedido.limpaPedido(pedido);
        }
        return gson.toJson(pedido != null ? pedido : false);
    }

    @GET
    @Path("buscaPorFilial/{idFilial}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPedidos(@PathParam("idFilial") int id) {
        List<Pedido> listaPedido = ControlePedido.buscaPorFilial(id);
        return gson.toJson(ControlePedido.limpaPedido(listaPedido));
    }

    @PUT
    @Path("grava")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public String putPedido(String content) {
        Pedido pedido;
        int cod;
        if (content.isEmpty()) {
            return null;
        }
        try {
            pedido = gson.fromJson(content, Pedido.class);
        } catch (JsonSyntaxException ex) {
            System.out.println(ex);
            return null;
        }
        if (pedido.getCodPedido() == null) {
            cod = ControlePedido.gravar(0, pedido.getCodFilial().getCodFilial(), pedido.getPedDtBaixa(), pedido.getPedDtRealizacao(), pedido.getProdutoList(),pedido.getCodTipoPedido(),pedido.getPedStatus());
        } else {
            cod = ControlePedido.gravar(pedido.getCodPedido(), pedido.getCodFilial().getCodFilial(), pedido.getPedDtBaixa(), pedido.getPedDtRealizacao(), pedido.getProdutoList(),pedido.getCodTipoPedido(),pedido.getPedStatus());
        }
        return gson.toJson(ControlePedido.limpaPedido(ControlePedido.busca(cod)));
    }

    @GET
    @Path("delete/{idPedido}")
    public String deleta(@PathParam("idPedido") int id) {
        if (ControlePedido.deleta(id)) {
            return gson.toJson(true);
        }
        return gson.toJson(false);

    }
}
