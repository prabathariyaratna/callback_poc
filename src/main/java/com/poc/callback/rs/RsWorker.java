package com.poc.callback.rs;

import com.poc.callback.util.Base64Util;
import com.poc.callback.util.RestClient;

/**
 * Created by ahach on 13/03/2017.
 */
public class RsWorker implements Runnable {
    private String callbackUrl, callbackId;

    public RsWorker(String callbackUrl, String callBackId) {
        this.callbackUrl = callbackUrl;
        this.callbackId = callBackId;
    }
    public void run() {
        try {
            Thread.sleep(5000);
            if(callbackUrl != null) {
                String url = Base64Util.decode(callbackUrl);
                RestClient.post(url, "Received", callbackId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
