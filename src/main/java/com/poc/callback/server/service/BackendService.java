package com.poc.callback.server.service;

import com.poc.callback.util.Base64Util;
import com.poc.callback.util.RestClient;

/**
 * Created by ahach on 13/03/2017.
 */
public class BackendService implements Runnable {
    private String callbackUrl, callbackId;

    public BackendService(String callbackUrl, String callBackId) {
        this.callbackUrl = callbackUrl;
        this.callbackId = callBackId;
    }
    public void run() {
        try {
            Thread.sleep(10000);

            if(callbackUrl != null) {
                String url = Base64Util.decode(callbackUrl);
                RestClient.post(url, "Received", callbackId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
