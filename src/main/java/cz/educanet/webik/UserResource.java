package cz.educanet.webik;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("users") //localhost:8080/webik/api/users
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static List<String> users = new ArrayList<>();


    @GET //[GET] localhost:8080/webik/api/users
    public Response getAll() {

        return Response.ok(users).build();
    }

    @POST
    public Response createUser(@QueryParam("name") String name) {
        if(!userExists(name)) {
            users.add(name);
            return Response.ok().build();
        }
        else return Response.status(400, "Týpeček existuje").build();
    }
    @Path("users/{name}")
    @PUT
    public Response renameUser(@QueryParam("name") String newName, @PathParam("name") String name) {
        if(userExists(name)){
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).equals(name)) {
                    users.set(i, newName);
                    return Response.ok("Týpeček " + name + " byl přejmenován na " + newName).build();
                }
            }
        }
        else return Response.status(400, "Týpeček neexistuje").build();
        return Response.status(304, "Nic se nezměnilo").build();
    }
    @DELETE
    public Response deleteUser(String name) {
        if(userExists(name)) {
            for(int i=0; i < users.size(); i++) {
                if(users.get(i).equals(name)) {
                    users.remove(i);
                    return Response.ok("Týpeček " + name + " byl vymazán z tohoto světa").build();
                }
            }
        }
        else return Response.status(400, "Týpeček neexistuje").build();
        return Response.status(304, "Nic se nezměnilo").build();
    }

    public boolean userExists(String uName) {
        for(int i=0; i < users.size(); i++) {
             if(users.get(i).equals(uName)) return true;
        }
        return false;
    }
}

//UserResource us = new UserResource();
//us.name;
//UserResource us2 = new UserResource();
//us2.name;
