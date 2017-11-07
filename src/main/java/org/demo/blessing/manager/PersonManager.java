package org.demo.blessing.manager;

import org.demo.blessing.model.Person;

import java.util.List;

/**
 * Created by zhaol on 2017/11/1.
 */
public interface PersonManager {

    List<Person> selectAllPerson();

    void addPerson(Person person);

}
