package com.poc.callback.cs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ahach on 13/03/2017.
 */
public class CallBackStore {
    private static CallBackStore instance = null;
    private Map<String, ServerWorkerThread> callbackStore = null;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private CallBackStore() {
        callbackStore = new HashMap<String, ServerWorkerThread>();
    }
    public static CallBackStore getInstance() {
        if(instance == null) {
            instance = new CallBackStore();
        }
        return instance;
    }

    public void add(String callbackId, ServerWorkerThread workerThread) {
        writeLock.lock();
        try {
            callbackStore.put(callbackId, workerThread);
        } finally {
            writeLock.unlock();
        }
    }

    public void remove(String callbackId) {
        writeLock.lock();
        try {
            callbackStore.remove(callbackId);
        } finally {
            writeLock.unlock();
        }
    }

    public ServerWorkerThread get(String callbackId) {
        readLock.lock();
        try {
            return callbackStore.get(callbackId);
        } finally {
            readLock.unlock();
        }
    }

    public void check() {
        writeLock.lock();
        try {
            for (String callbackId : callbackStore.keySet()) {
                if (callbackStore.get(callbackId).isExpire()) {
                    ServerWorkerThread serverWorkerThread = callbackStore.get(callbackId);
                    callbackStore.remove(callbackId);
                    System.out.println("Callback [" + callbackId +"] removed");
                    serverWorkerThread.setStatus(false);
                    serverWorkerThread.getSemaphore().release();
                }
            }
        } finally {
            writeLock.unlock();
        }
    }



}
