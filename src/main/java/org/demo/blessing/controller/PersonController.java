package org.demo.blessing.controller;

import org.demo.blessing.manager.PersonManager;
import org.demo.blessing.model.Person;
import org.demo.blessing.util.IDCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhaol on 2017/11/3.
 */
@Controller
public class PersonController {

    @Autowired
    @Qualifier(value = "personManager")
    private PersonManager personManager;

    @RequestMapping(value = "/person", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addPerson(Person person) {
        personManager.addPerson(person);
        return "success";
    }

    @RequestMapping(value = "/findAllPerson", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String findAllPerson(Model model) {
        List<Person> persons = personManager.findAllPerson();
        model.addAttribute("persons", persons);
        return "allPerson";
    }

    @RequestMapping(value = "/modifyPersonBefore/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String modifyPersonBefore(Model model, @PathVariable Integer id) {
        Person person = personManager.findPersonById(id);
        model.addAttribute("person", person);
        return "modifyPerson";
    }

    @RequestMapping(value = "/modifyPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String modifyPerson(Person person) {

        System.out.println(person.toString());
        personManager.modifyPerson(person);
        return "success";
    }

    @RequestMapping(value = "/getPersonInfoByIdcard", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getPersonInfoByIdcard(@RequestBody String idCard) {
        boolean flag = IDCardUtil.checkIdCard(idCard);
        String sex = null;
        String address = null;
        int age = 0;
        if (flag) {
            sex = IDCardUtil.parseSex(idCard);
            age = IDCardUtil.parseAge(idCard);
            address = IDCardUtil.parseAddress(idCard);
        }
        return "{'sex':'"+sex+"','age':'"+age+"','address':'"+address+"'}";
    }

}
