package com.poc.callback.client;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CallbackCleaningTask implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        CallBackStore.getInstance().check();
    }
}
