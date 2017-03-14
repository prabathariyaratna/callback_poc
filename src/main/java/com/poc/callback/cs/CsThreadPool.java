package com.poc.callback.cs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ahach on 13/03/2017.
 */
public class CsThreadPool {

    private static CsThreadPool instance = null;

    private CsThreadPool() {

    }

    public static CsThreadPool getInstance() {
        if(instance == null) {
            instance = new CsThreadPool();
        }

        return instance;
    }

    public ExecutorService getExecutorService() {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        return executor;
    }
}
