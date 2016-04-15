/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;

/**
 *
 * @author Maurílio
 */
@Provider
public class Resposta implements ContainerResponseFilter{
//    public static Response buildResponse(JsonObject json){
//        return Response.ok().entity(json.toString()).header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
//                .allow("OPTIONS").build();
//    }
//    
//    public static Response buildResponse(String json){
//        return Response.ok().entity(json).header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
//                .allow("OPTIONS").build();
//    }
//    
//    public static Response buildNotOKResponse(){
//        return Response.serverError().build();
//    }

    public ContainerResponse filter(ContainerRequest request,
            ContainerResponse response) {

        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        return response;
    }

    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext crc1) throws IOException {
        Logger.getLogger("com.example").log( Level.INFO, "before: {0}", crc1.getHeaders());
        crc1.getHeaders().add("Access-Control-Allow-Origin", "*");
        crc1.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        crc1.getHeaders().add("Access-Control-Allow-Credentials", "true");
        crc1.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        crc1.getHeaders().add("Access-Control-Max-Age", "1209600");
        Logger.getLogger("com.example").log( Level.INFO, "after: {0}", crc1.getHeaders());
    }
}
