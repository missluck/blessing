package org.demo.blessing.controller;

import org.demo.blessing.manager.QuartzManager;
import org.demo.blessing.task.MyTask;
import org.demo.blessing.util.IDCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaol on 2017/11/7.
 */
@RestController
public class TestController {

    @Autowired
    @Qualifier(value = "quartzManager")
    private QuartzManager quartzManager;

    @RequestMapping(value = "/addJob", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String testAddJob() {
        quartzManager.addJob("testJobName", "testTriggerName", MyTask.class, "0/3 * * * * ?", "15100033404", "祝你生日快乐!");
        return "success";
    }

    @RequestMapping(value = "/modifyJob", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String testModifyJob() {
        quartzManager.modifyJob("testJobName", "testTriggerName", MyTask.class, "0/6 * * * * ?");
        return "success";
    }

    @RequestMapping(value = "/removeJob", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String testRemoveJob() {
        quartzManager.removeJob("testJobName", "testTriggerName");
        return "success";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String test() {

        String cid = "411323198308012652";
        //String cid = "220282199407072610";
        System.out.println("身份证号：\t"+cid);
        boolean flag = IDCardUtil.checkIdCard(cid);
        System.out.println("身份证号是否通过校验：\t"+flag);
        String gender = IDCardUtil.parseSex(cid);
        System.out.println("性别：\t"+gender);
        int age = IDCardUtil.parseAge(cid);
        System.out.println("年龄：\t"+age);
        String address = IDCardUtil.parseAddress(cid);
        System.out.println("出生地：\t"+address);

        return "success";
    }

}
