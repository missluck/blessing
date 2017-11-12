package org.demo.blessing.mapper;

import org.demo.blessing.model.BlessTask;

import java.util.List;

/**
 * Created by zhaol on 2017/11/3.
 */
public interface BlessTaskMapper {

    List<BlessTask> selectAllBlessTask();

    int insertBlessTask(BlessTask blessTask);

    BlessTask selectBlessTaskById(Integer id);

    int updateBlessTask(BlessTask blessTask);

    int deleteBlessTask(Integer id);

}
