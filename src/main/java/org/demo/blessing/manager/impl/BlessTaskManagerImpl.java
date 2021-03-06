package org.demo.blessing.manager.impl;

import org.demo.blessing.manager.BlessTaskManager;
import org.demo.blessing.manager.QuartzManager;
import org.demo.blessing.mapper.BlessTaskMapper;
import org.demo.blessing.mapper.PersonMapper;
import org.demo.blessing.model.BlessTask;
import org.demo.blessing.task.MyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaol on 2017/11/7.
 */
@Service(value = "blessTaskManager")
public class BlessTaskManagerImpl implements BlessTaskManager {

    @Autowired
    @Qualifier(value = "blessTaskMapper")
    private BlessTaskMapper blessTaskMapper;
    @Autowired
    @Qualifier(value = "personMapper")
    private PersonMapper personMapper;
    @Autowired
    @Qualifier(value = "quartzManager")
    private QuartzManager quartzManager;

    @Override
    public String getCronExpression(Date date, String dateFormat) {
        return formatDateByPattern(date, dateFormat);
    }

    @Override
    public void addBlessTask(BlessTask blessTask) {
        //先保存数据，再添加定时任务。
        blessTaskMapper.insertBlessTask(blessTask);
        String phone = personMapper.selectPhoneByPersonid(blessTask.getPersonId());
        quartzManager.addJob(blessTask.getJobName(), blessTask.getTriggerName(), MyTask.class, blessTask.getCron(), phone,blessTask.getBlessContent());
    }

    /**
     * 通过时间获取定时器cron表达式
     * @param date 定时时间
     * @param dateFormat ss mm HH dd MM ? yyyy
     * @return 定时器cron表达式
     */
    public String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    @Override
    public List<BlessTask> findAllBlessTask() {
        return blessTaskMapper.selectAllBlessTask();
    }

    @Override
    public BlessTask findBlessTaskById(Integer id) {
        return blessTaskMapper.selectBlessTaskById(id);
    }

    @Override
    public void modifyBlessTask(BlessTask blessTask) {
        blessTaskMapper.updateBlessTask(blessTask);
        String phone = personMapper.selectPhoneByPersonid(blessTask.getPersonId());
        quartzManager.modifyJob(blessTask.getJobName(), blessTask.getTriggerName(), MyTask.class, blessTask.getCron(), phone, blessTask.getBlessContent());
    }

    @Override
    public void removeBlessTask(Integer id) {
        BlessTask blessTask = blessTaskMapper.selectBlessTaskById(id);
        quartzManager.removeJob(blessTask.getJobName(), blessTask.getTriggerName());
        blessTaskMapper.deleteBlessTask(id);
    }

}
