package org.demo.blessing.manager;

import org.demo.blessing.model.Person;

import java.util.List;

/**
 * Created by zhaol on 2017/11/1.
 */
public interface PersonManager {

    void addPerson(Person person);

    List<Person> findAllPerson();

    Person findPersonById(Integer id);

    void modifyPerson(Person person);

}
