package org.demo.blessing.manager;

import org.demo.blessing.model.BlessTask;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaol on 2017/11/7.
 */
public interface BlessTaskManager {

    String getCronExpression(Date date, String dateFormat);

    void addBlessTask(BlessTask blessTask);

    List<BlessTask> findAllBlessTask();

    BlessTask findBlessTaskById(Integer id);

    void modifyBlessTask(BlessTask blessTask);

    void removeBlessTask(Integer id);

}
