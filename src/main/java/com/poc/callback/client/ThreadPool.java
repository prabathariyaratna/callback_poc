package com.poc.callback.client;

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

    public ExecutorService getExecutorService() {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        return executor;
    }
}
