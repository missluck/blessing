package org.demo.blessing.model;

/**
 * Created by zhaol on 2017/11/1.
 */
public class Person {
    private Integer id;
    private String name;
    private String sex;
    private String age;
    private String address;
    private String phone;
    private String idCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", sex='" + sex + '\'' + ", age='" + age + '\'' + ", address='" + address + '\'' + ", phone='" + phone + '\'' + ", idCard='" + idCard + '\'' + '}';
    }
}
