package com.poc.callback.client;

import com.poc.callback.util.Base64Util;
import com.poc.callback.util.RestClient;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ahach on 10/03/2017.
 */
public class ClientWorkerThread implements Runnable {

    private String callbackId;

    public ClientWorkerThread(String callbackId) {
        this.callbackId = callbackId;
    }

    public void run() {
        if(CallBackStore.getInstance().get(callbackId) != null) {
            ServerWorkerThread serverWorker = CallBackStore.getInstance().get(callbackId);
            CallBackStore.getInstance().remove(callbackId);
            serverWorker.getSemaphore().release();
            System.out.println("================ Lock released ====================");
        } else {
            System.out.println("================ Callback already removed =====================" + callbackId);
        }
    }

}
