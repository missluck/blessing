package org.demo.blessing.manager.impl;

import org.demo.blessing.manager.PersonManager;
import org.demo.blessing.mapper.PersonMapper;
import org.demo.blessing.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaol on 2017/11/1.
 */
@Service(value = "personManager")
public class PersonManagerImpl implements PersonManager {

    @Autowired
    @Qualifier(value = "personMapper")
    private PersonMapper personMapper;

    @Override
    public List<Person> selectAllPerson() {
        return personMapper.selectAllPerson();
    }
}
