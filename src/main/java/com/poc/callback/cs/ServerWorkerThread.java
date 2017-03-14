package com.poc.callback.cs;

import com.poc.callback.util.Base64Util;
import com.poc.callback.util.RestClient;

import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Semaphore;

/**
 * Created by ahach on 13/03/2017.
 */
public class ServerWorkerThread {

    private Date createdTime;
    private static String CALLBACK_URL = "http://localhost:8091/services/callback";
    private static String ACTUAL_URL = "http://localhost:8090/users/123";
    private Semaphore semaphore = null;
    private boolean status = true;

    public ServerWorkerThread(Semaphore semaphore) {
        createdTime = new Date();
        this.semaphore = semaphore;
    }

    public Response send() {
        Response response = sendRequest();
        if(status) {
            System.out.println("================================ Finally Successful =================================");
        } else {
            System.out.println("================================ Finally UnSuccessful=================================");
        }
        return response;
    }

    public boolean isExpire() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdTime);
        calendar.add(Calendar.MINUTE, 1);
        if(new Date().getTime() > calendar.getTime().getTime()) {
            return true;
        }

        return false;
    }

    private Response sendRequest() {
        String callbackId = UUID.randomUUID().toString();
        String encodedUrl = Base64Util.encode(CALLBACK_URL);

        try {
            CallBackStore.getInstance().add(callbackId, this);
            Response response = RestClient.get(ACTUAL_URL, callbackId, encodedUrl);
            System.out.println("response received =================" + response);
            if(response != null && response.getStatus() == 200) {
                System.out.println("Lock acquired");
                semaphore.acquire();
                return Response.status(200).entity("OK").build();
            } else {
                return Response.status(500).entity("Fail").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Fail").build();
        }
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
