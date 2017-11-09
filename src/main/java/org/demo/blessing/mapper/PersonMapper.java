package org.demo.blessing.mapper;

import org.demo.blessing.model.Person;

import java.util.List;

/**
 * Created by zhaol on 2017/11/1.
 */
public interface PersonMapper {

    int insertPerson(Person person);

    List<Person> selectAllPerson();

    Person selectPersonById(Integer id);

    int updatePerson(Person person);

    String selectPhoneByPersonid(Integer personid);

}
