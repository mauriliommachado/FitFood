/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import control.ControlePessoa;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Pessoa;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServicePessoa")
public class ServicePessoa {

    @Context
    private UriInfo context;
    private Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();

    public ServicePessoa() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("busca/{idPessoa}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPessoa(@PathParam("idPessoa") int id) {
        Pessoa pessoa = ControlePessoa.busca(id);
        if (pessoa != null) {
            ControlePessoa.limpaPessoa(pessoa);
        }
        return gson.toJson(pessoa != null ? pessoa : false);
    }

    @GET
    @Path("buscaPorEmpresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPessoasPorEmpresa(@PathParam("idEmpresa") int codEmpresa) {
        List<Pessoa> listaPessoa = ControlePessoa.buscaPorEmpresa(codEmpresa);
        return gson.toJson(ControlePessoa.limpaPessoa(listaPessoa));
    }
    
     @GET
    @Path("buscaPorTelefone/{numTelefone}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPessoaPorTelefone(@PathParam("numTelefone") String numTelefone) {
         Pessoa pessoa = ControlePessoa.buscaPorTelefone(numTelefone);
        return gson.toJson(ControlePessoa.limpaPessoa(pessoa));
    }

    @PUT
    @Path("grava")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public String putPessoa(String content) {
        Pessoa pessoa;
        int cod;
        if (content.isEmpty()) {
            return null;
        }
        try {
            pessoa = gson.fromJson(content, Pessoa.class);
        } catch (JsonSyntaxException ex) {
            System.out.println(ex);
            return null;
        }
        if (pessoa.getCodPessoa() == 0) {
            cod = ControlePessoa.gravar(0, pessoa.getCodEmpresa().getCodEmpresa(), pessoa.getCodTipoPessoa(), pessoa.getEnderecoList(), pessoa.getPesAtivo(), pessoa.getPesCPFCNPJ(), new Date(), pessoa.getPesEmail(), pessoa.getPesFisica(), pessoa.getPesNome(), pessoa.getPesSenha(), pessoa.getPesSexo());
        } else {
            cod = ControlePessoa.gravar(pessoa.getCodPessoa(), pessoa.getCodEmpresa().getCodEmpresa(), pessoa.getCodTipoPessoa(), pessoa.getEnderecoList(), pessoa.getPesAtivo(), pessoa.getPesCPFCNPJ(), pessoa.getPesDtCadastro(), pessoa.getPesEmail(), pessoa.getPesFisica(), pessoa.getPesNome(), pessoa.getPesSenha(), pessoa.getPesSexo());
        }
        return gson.toJson(ControlePessoa.limpaPessoa(ControlePessoa.busca(cod)));
    }

    @GET
    @Path("delete/{idPessoa}")
    public String deleta(@PathParam("idPessoa") int id) {
        if (ControlePessoa.deleta(id)) {
            return gson.toJson(true);
        }
        return gson.toJson(false);
    }

}

class GsonUTCDateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final DateFormat dateFormat;

    public GsonUTCDateAdapter() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());      //This is the format I need
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));                               //This is the key line which converts the date to UTC which cannot be accessed with the default serializer
    }

    @Override
    public synchronized JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateFormat.format(date));
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(jsonElement.getAsString());
        } catch (ParseException ex) {
//            Logger.getLogger(GsonUTCDateAdapter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return  convertedCurrentDate;
    }
}
