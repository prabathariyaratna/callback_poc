package com.poc.callback.server.service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserService {

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") String userId, @HeaderParam("callBackUrl") String callbackUrl, @HeaderParam("callbackId") String callbackId) {
        System.out.println("======================== request got================= userId =" + userId + "==callbackUrl = "+callbackUrl+"===callbackId="+callbackId);
        ThreadPool.getInstance().getEecuExecutorService().execute(new BackendService(callbackUrl, callbackId));
        return Response.status(200).entity("receive").build();
    }
}
