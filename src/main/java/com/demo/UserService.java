/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo;

/**
 *
 * @author surajit.samui
 */
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserService {

    @POST
    @Path("/add")
    public Response addUser(@FormParam("name") String name, @FormParam("age") int age) {
        return Response.status(200).entity("addUser is called, name : " + name + ", age : " + age).build();
    }

}
