package com.poc.callback.util;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

    public static String post(String url, String payload, String callbackId) throws Exception {
        System.out.println("==================POST Request ================ url :" + url + "==== callbackId :" + callbackId);
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(url);
            Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN);
        Response response = invocationBuilder.header("callbackId",callbackId).post(Entity.entity(payload, MediaType.TEXT_PLAIN));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            return response.getEntity().toString();
    }

    public static Response get(String url, String callbackId, String callBackUrl) throws Exception{
        System.out.println("==================GET Request ================ url :" + url + "==== callbackId :" + callbackId +"===callBackUrl :"+callBackUrl);
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(url);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.TEXT_PLAIN);
        return invocationBuilder.header("callbackId",callbackId).header("callbackUrl", callBackUrl).get();
    }
}
