package org.demo.blessing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.blessing.manager.BlessTaskManager;
import org.demo.blessing.manager.PersonManager;
import org.demo.blessing.model.BlessTask;
import org.demo.blessing.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaol on 2017/11/7.
 */
@Controller
public class BlessTaskController {

    @Autowired
    @Qualifier(value = "blessTaskManager")
    private BlessTaskManager blessTaskManager;
    @Autowired
    @Qualifier(value = "personManager")
    private PersonManager personManager;

    @RequestMapping(value = "/addBlessTaskBefore", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String addBlessTaskBefore(Model model) {
        List<Person> persons = personManager.findAllPerson();
        model.addAttribute("persons", persons);
        return "addBlessTask";
    }

    @RequestMapping(value = "/addBlessTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addBlessTask(BlessTask blessTask) {
        blessTaskManager.addBlessTask(blessTask);
        return "success";
    }

    @RequestMapping(value = "/getCronExpression", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getCronExpression(@RequestBody String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        String cronExpression = null;
        try {
            Date date = dateFormat.parse(dateStr);
            cronExpression = blessTaskManager.getCronExpression(date, "ss mm HH dd MM ? yyyy");
        } catch (ParseException e) {
            //e.printStackTrace();
            return "{'status':'error'}";
        }
        return "{'status':'success','cron':'"+cronExpression+"'}";
    }

    @RequestMapping(value = "/findAllBlessTask", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String findAllBlessTask(Model model) {
        List<BlessTask> blessTasks = blessTaskManager.findAllBlessTask();
        model.addAttribute("blessTasks", blessTasks);
        return "allBlessTask";
    }

    @RequestMapping(value = "/modifyBlessTaskBefore/{id}")
    public String modifyBlessTaskBefore(Model model, @PathVariable Integer id) {
        BlessTask blessTask = blessTaskManager.findBlessTaskById(id);
        List<Person> persons = personManager.findAllPerson();
        model.addAttribute("blessTask",blessTask);
        model.addAttribute("persons", persons);
        return "modifyBlessTask";
    }

    @RequestMapping(value = "/modifyBlessTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String modifyBlessTask(BlessTask blessTask) {
        blessTaskManager.modifyBlessTask(blessTask);
        return "success";
    }

    @RequestMapping(value = "/removeBlessTask/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String removeBlessTask(@PathVariable Integer id) {
        blessTaskManager.removeBlessTask(id);
        return "success";
    }

}
