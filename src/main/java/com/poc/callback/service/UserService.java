package com.poc.callback.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserService {

    @GET
    @Path("/{userId}")
    public com.poc.callback.service.Response getUser(@PathParam("userId") String userId, @QueryParam("callId") String callId) {
        com.poc.callback.service.Response response = new com.poc.callback.service.Response(Integer.parseInt(callId), "hello" + userId);
        return response;
    }
}
