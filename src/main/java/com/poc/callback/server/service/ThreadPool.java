package com.poc.callback.server.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ahach on 13/03/2017.
 */
public class ThreadPool {

    private static ThreadPool instance = null;

    private ThreadPool() {

    }

    public static ThreadPool getInstance() {
        if(instance == null) {
            instance = new ThreadPool();
        }

        return instance;
    }

    public ExecutorService getEecuExecutorService() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        return executor;
    }
}
