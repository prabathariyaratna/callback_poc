package com.poc.callback.rs;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/users")
public class RsService {

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") String userId, @HeaderParam("callBackUrl") String callbackUrl, @HeaderParam("callbackId") String callbackId) {
        System.out.println("======================== request got================= userId =" + userId + "==callbackUrl = " + callbackUrl + "===callbackId=" + callbackId);
        RsThreadPool.getInstance().getEecuExecutorService().execute(new RsWorker(callbackUrl, callbackId));
        return Response.status(200).entity("receive").build();
    }
}
