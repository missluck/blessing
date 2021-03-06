package org.demo.blessing.model;

/**
 * Created by zhaol on 2017/11/1.
 */
public class BlessTask {
    private Integer id;
    private String blessContent;
    private String jobName;
    private String jobGroupName;
    private String triggerName;
    private String triggerGroupName;
    private String cron;
    private Integer personId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlessContent() {
        return blessContent;
    }

    public void setBlessContent(String blessContent) {
        this.blessContent = blessContent;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "BlessTask{" + "id=" + id + ", blessContent='" + blessContent + '\'' + ", jobName='" + jobName + '\'' + ", jobGroupName='" + jobGroupName + '\'' + ", triggerName='" + triggerName + '\'' + ", triggerGroupName='" + triggerGroupName + '\'' + ", cron='" + cron + '\'' + ", personId=" + personId + '}';
    }
}
