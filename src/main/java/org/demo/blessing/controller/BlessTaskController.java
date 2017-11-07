package org.demo.blessing.controller;

import org.demo.blessing.manager.BlessTaskManager;
import org.demo.blessing.model.BlessTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhaol on 2017/11/7.
 */
@Controller
public class BlessTaskController {

    @Autowired
    @Qualifier(value = "blessTaskManager")
    private BlessTaskManager blessTaskManager;

    @RequestMapping(value = "/addBlessTaskBefore", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public  String addBlessTaskBefore() {

        return "addBlessTask";
    }

    @RequestMapping(value = "/findAllBlessTask", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String findAllBlessTask() {
        List<BlessTask> blessTasks = blessTaskManager.findAllBlessTask();
        for (BlessTask bt:blessTasks) {
            System.out.println(bt.toString());
        }
        return "allBlessTask";
    }

}
