package com.poc.callback.cs;


import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.concurrent.Semaphore;


@Path("/services")
public class CsService {

    @POST
    @Path("/callback")
    public Response callback(String value, @HeaderParam("callbackId") String callbackId) {

        ClientWorkerThread clientWorkerThread = new ClientWorkerThread(callbackId);
        CsThreadPool.getInstance().getExecutorService().execute(clientWorkerThread);
        String output = "Received";
        return Response.status(200).entity(output).build();

    }

    @GET
    @Path("/test")
    public Response test() {
        Semaphore semaphore = new Semaphore(0);
        ServerWorkerThread serverWorkerThread = new ServerWorkerThread(semaphore);
        return serverWorkerThread.send();
    }
}
