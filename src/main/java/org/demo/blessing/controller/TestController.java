package org.demo.blessing.controller;

import org.demo.blessing.manager.PersonManager;
import org.demo.blessing.manager.QuartzManager;
import org.demo.blessing.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhaol on 2017/11/1.
 */
@Controller
public class TestController {

    @Autowired
    @Qualifier(value = "quartzManager")
    private QuartzManager quartzManager;
    @Autowired
    @Qualifier(value = "personManager")
    private PersonManager personManager;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String test() {
        List<Person> list = personManager.selectAllPerson();
        for (Person person:list) {
            System.out.println(person.toString());
        }
        return "success";
    }

}
