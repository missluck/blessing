package org.demo.blessing.task;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaol on 2017/11/1.
 */
public class MyTask implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis()))+"Say Hello World!");
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String[] params = (String[]) dataMap.get("params");
        for (int i=0; i<params.length; i++) {
            System.out.println(params[i]);
        }

    }

}
