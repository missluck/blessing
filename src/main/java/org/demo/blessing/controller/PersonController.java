package org.demo.blessing.controller;

import org.demo.blessing.manager.PersonManager;
import org.demo.blessing.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhaol on 2017/11/3.
 */
@Controller
public class PersonController {

    @Autowired
    @Qualifier(value = "personManager")
    private PersonManager personManager;

    @RequestMapping(value = "person", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addPerson(Person person) {

        System.out.println(person.toString());
        return "success";
    }

}
