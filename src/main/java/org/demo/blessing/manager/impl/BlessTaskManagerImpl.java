package org.demo.blessing.manager.impl;

import org.demo.blessing.manager.BlessTaskManager;
import org.demo.blessing.mapper.BlessTaskMapper;
import org.demo.blessing.model.BlessTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaol on 2017/11/7.
 */
@Service(value = "blessTaskManager")
public class BlessTaskManagerImpl implements BlessTaskManager {

    @Autowired
    @Qualifier(value = "blessTaskMapper")
    private BlessTaskMapper blessTaskMapper;

    @Override
    public List<BlessTask> findAllBlessTask() {
        return blessTaskMapper.selectAllBlessTask();
    }

}
