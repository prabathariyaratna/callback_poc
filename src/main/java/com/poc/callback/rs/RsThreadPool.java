package com.poc.callback.rs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ahach on 13/03/2017.
 */
public class RsThreadPool {
    private static RsThreadPool instance = null;

    private RsThreadPool() {
    }

    public static RsThreadPool getInstance() {
        if (instance == null) {
            instance = new RsThreadPool();
        }

        return instance;
    }

    public ExecutorService getEecuExecutorService() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        return executor;
    }
}
