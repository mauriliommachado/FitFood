/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Maurílio
 */
@Path("ServiceLogin")
public class ServiceLogin {

    @Context
    private UriInfo context;
    /**
     * Creates a new instance of Hello
     */
    public ServiceLogin() {
    }

    /**
     * Retrieves representation of an instance of service.Hello
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getText() {
        return "Hello";
    }

    @GET
    @Path("getStatus")
    @Produces("application/json")
    public String getStatus() {
        JsonObject json = new JsonObject();
        json.addProperty("status", 1);
        return json.toString();
    }

}
