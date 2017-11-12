package org.demo.blessing.manager;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component(value = "quartzManager")
public class QuartzManager {

    //@Autowired
    //private Scheduler scheduler;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    private static String DEFAULT_JOBGROUP_NAME = "DEFAULT_JOBGROUP_NAME";
    private static String DEFAULT_TRIGGERGROUP_NAME = "DEFAULT_TRIGGERGROUP_NAME";

    public void addJob(String jobName, String triggerName, Class jobClass, String cron, String... params) {
        addJob(jobName, DEFAULT_JOBGROUP_NAME, triggerName, DEFAULT_TRIGGERGROUP_NAME, jobClass, cron, params);
    }

    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron, String... params) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(jobName, jobGroupName);
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            //System.out.println("job is boolean："+scheduler.checkExists(jobKey)+"\t"+"trigger is boolean："+scheduler.checkExists(triggerKey));
            if (scheduler.checkExists(triggerKey)) return;
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            JobDataMap dataMap = jobDetail.getJobDataMap();
            dataMap.put("params", params);
            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void removeJob(String jobName, String triggerName) {
        removeJob(jobName, DEFAULT_JOBGROUP_NAME, triggerName, DEFAULT_TRIGGERGROUP_NAME);
    }

    public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            JobKey jobKey = new JobKey(jobName, jobGroupName);
            if (scheduler.checkExists(triggerKey)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
            }
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void modifyJob(String jobName, String triggerName, Class jobClass, String cron, String... params) {
        modifyJob(jobName, DEFAULT_JOBGROUP_NAME, triggerName, DEFAULT_TRIGGERGROUP_NAME, jobClass, cron, params);
    }

    public void modifyJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass,String cron, String... params) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = new JobKey(jobName, jobGroupName);
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            if (scheduler.checkExists(jobKey)&&scheduler.checkExists(triggerKey)) {
                removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                addJob(jobName, triggerName, jobClass, cron, params);
            } else {
                addJob(jobName, triggerName, jobClass, cron, params);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void startJobs() {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if(!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void shutdownJobs() {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if(!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


}
