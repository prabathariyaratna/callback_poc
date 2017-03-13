package com.poc.callback.client;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CallbackServer {

    public static void main(String args[]) throws Exception {
        ThreadPool.getInstance().getExecutorService().submit(new JettyServer());
        startCallbackCleaningTask();
    }


    private static  void startCallbackCleaningTask() throws Exception {
        JobDetail job = JobBuilder.newJob(CallbackCleaningTask.class)
                .withIdentity("dummyJobName", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5).repeatForever())
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        System.out.println("======================== Cleaning sheduler starting ==============");
        scheduler.scheduleJob(job, trigger);
        System.out.println("======================== Cleaning sheduler started ==============");
    }

}
