/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import control.ControleEndereco;
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
import model.Endereco;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServiceEndereco")
public class ServiceEndereco {

    @Context
    private UriInfo context;
    private final Gson gson = new Gson();

    public ServiceEndereco() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idEndereco}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEndereco(@PathParam("idEndereco") int id) {
        Endereco endereco = ControleEndereco.busca(id);
        if (endereco != null) {
            ControleEndereco.limpaEndereco(endereco);
        }
        return gson.toJson(endereco != null ? endereco : false);
    }

    @GET
    @Path("busca")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEnderecos() {
        List<Endereco> listaEndereco = ControleEndereco.busca();
        return gson.toJson(ControleEndereco.limpaEndereco(listaEndereco));
    }

    @PUT
    @Path("grava")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public String putEndereco(String content) {
        Endereco endereco;
        int cod;
        if (content.isEmpty()) {
            return null;
        }
        try {
            endereco = gson.fromJson(content, Endereco.class);
        } catch (JsonSyntaxException ex) {
            System.out.println(ex);
            return null;
        }
        if (endereco.getCodEndereco() == null) {
            cod = ControleEndereco.gravar(0,endereco.getCodPessoa().getCodPessoa(),endereco.getEndBairro(),endereco.getEndCep(),endereco.getEndCidade(),endereco.getEndComplemento(),endereco.getEndLogradouro(),endereco.getEndNumero(),endereco.getEndReferencia(),endereco.getEndUF());
        } else {
            cod = ControleEndereco.gravar(endereco.getCodEndereco(),endereco.getCodPessoa().getCodPessoa(),endereco.getEndBairro(),endereco.getEndCep(),endereco.getEndCidade(),endereco.getEndComplemento(),endereco.getEndLogradouro(),endereco.getEndNumero(),endereco.getEndReferencia(),endereco.getEndUF());
        }
        return gson.toJson(ControleEndereco.limpaEndereco(ControleEndereco.busca(cod)));
    }

    @GET
    @Path("delete/{idEndereco}")
    public String deleta(@PathParam("idEndereco") int id) {
        if (ControleEndereco.deleta(id)) {
            return gson.toJson(true);
        }
        return gson.toJson(false);

    }
}
